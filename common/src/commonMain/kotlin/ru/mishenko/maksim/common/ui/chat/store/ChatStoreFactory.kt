package ru.mishenko.maksim.common.ui.chat.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mishenko.maksim.common.dispatcher
import ru.mishenko.maksim.common.domain.MessageController

class ChatStoreFactory(private val storeFactory: StoreFactory) {
    private val messageController by lazy { MessageController.builder.build() }

    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): ChatStore =
        object : ChatStore, Store<ChatStore.Intent, ChatStore.State, Nothing> by storeFactory.create(
            name = "ChatStore",
            initialState = ChatStore.State(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
            bootstrapper = coroutineBootstrapper {
                launch(Dispatchers.Default) {
                    MessageController.builder.setScope(scope = this)
                    messageController.flow().collect { message ->
                        withContext(dispatcher()) {
                            dispatch(Action.EmitMessage(message))
                        }
                    }
                }
            }
        ) {}

    sealed interface Action {
        data class EmitMessage(val newMessage: String) : Action
    }

    sealed interface Message {
        data class UpdateMessage(val message: String) : Message
        data class UpdateMessageList(val list: List<String>) : Message
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<ChatStore.Intent, Action, ChatStore.State, Message, Nothing>() {
        override fun executeIntent(intent: ChatStore.Intent, getState: () -> ChatStore.State) {
            when (intent) {
                is ChatStore.Intent.OnInputMessage -> dispatch(Message.UpdateMessage(intent.message))
                is ChatStore.Intent.OnSendMessage -> scope.launch(dispatcher()) {
                    val state = getState()
                    messageController.sendMessage(state.message)
                    dispatch(Message.UpdateMessage(""))
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> ChatStore.State) {
            when (action) {
                is Action.EmitMessage -> scope.launch(dispatcher()) {
                    val state = getState()
                    dispatch(Message.UpdateMessageList(list = state.messageHistory + action.newMessage))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<ChatStore.State, Message> {
        override fun ChatStore.State.reduce(msg: Message): ChatStore.State =
            when (msg) {
                is Message.UpdateMessage -> copy(message = msg.message)
                is Message.UpdateMessageList -> copy(messageHistory = msg.list)
            }
    }
}