package ru.mishenko.maksim.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(val id: String, val text: String, val type: MessageType){
    companion object{
        val default = Message(id = "", text = "", type = MessageType.Other)

        fun String.toMessage() = Message(id = "", text = this, type = MessageType.Message)
    }
}