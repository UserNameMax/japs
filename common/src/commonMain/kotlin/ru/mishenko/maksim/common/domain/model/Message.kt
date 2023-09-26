package ru.mishenko.maksim.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(val id: String, val text: String,val author: String, val type: MessageType){
    companion object{
        val default = Message(id = "", text = "", author = "", type = MessageType.Other)

        fun String.toMessage() = Message(id = "", text = this, author = "", type = MessageType.Message)
    }
}