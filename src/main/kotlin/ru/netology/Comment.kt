package ru.netology

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Comment(
    val id: Int = 0,
    val fromId: Int,
    val toPostId: Int = 0,
    val date: LocalDateTime = LocalDateTime.now(),
    val text: String? = null,
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    val attachments: Array<Attachment> = emptyArray()
) {
    private val DEFAULT_TIME_FORMAT =
        DateTimeFormatter.ofPattern("HH:mm:ss, dd.MM.yyyy")
    var attachmentId = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return """Комментарий №${id + 1} к Посту №${toPostId + 1}:
            |-=< ${text ?: "пустой комментарий"} >=-
            |Разместил пользователь №$fromId
            |Время публикации - ${date.format(DEFAULT_TIME_FORMAT)}
            |${WallService.allAttachmentsToString(attachments)}
            """.trimMargin()
    }
}