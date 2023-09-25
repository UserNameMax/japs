package ru.mishenko.maksim.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class MessageType {
    Connect, ApproveConnect, Message, ApproveMessage, Other
}