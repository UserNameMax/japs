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
import ru.mishenko.maksim.common.domain.model.Message

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
                dispatch(Action.SetName(messageController.name))
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
        data class EmitMessage(val newMessage: Message) : Action
        data class SetName(val name: String) : Action
    }

    sealed interface UiMessage {
        data class UpdateMessage(val message: String) : UiMessage
        data class UpdateMessageList(val list: List<Message>) : UiMessage
        data class SetName(val name: String) : UiMessage
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<ChatStore.Intent, Action, ChatStore.State, UiMessage, Nothing>() {
        override fun executeIntent(intent: ChatStore.Intent, getState: () -> ChatStore.State) {
            when (intent) {
                is ChatStore.Intent.OnInputMessage -> dispatch(UiMessage.UpdateMessage(intent.message))
                is ChatStore.Intent.OnSendMessage -> scope.launch(dispatcher()) {
                    val state = getState()
                    messageController.sendMessage(state.message)
                    dispatch(UiMessage.UpdateMessage(""))
                }

                is ChatStore.Intent.OnChangeName -> {
                    messageController.name = intent.name
                    dispatch(UiMessage.SetName(intent.name))
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> ChatStore.State) {
            when (action) {
                is Action.EmitMessage -> scope.launch(dispatcher()) {
                    val state = getState()
                    dispatch(UiMessage.UpdateMessageList(list = state.messageHistory.filter { it.id != action.newMessage.id } + action.newMessage))
                }

                is Action.SetName -> dispatch(UiMessage.SetName(action.name))
            }
        }
    }

    private object ReducerImpl : Reducer<ChatStore.State, UiMessage> {
        override fun ChatStore.State.reduce(msg: UiMessage): ChatStore.State =
            when (msg) {
                is UiMessage.UpdateMessage -> copy(message = msg.message)
                is UiMessage.UpdateMessageList -> copy(messageHistory = msg.list)
                is UiMessage.SetName -> copy(name = msg.name)
            }
    }
}