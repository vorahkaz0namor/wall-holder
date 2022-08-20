package ru.netology

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class Post(
    val id: Int = 0,
    val fromId: Int,
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val text: String?,
    val viewsCount: Int? = null,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val ownerId: Int? = null,
    val isFavourite: Boolean = false,
    val likes: Likes = Likes(),
    val attachments: Array<Attachment>? = null
) {
    private val DEFAULT_TIME_FORMAT =
        DateTimeFormatter.ofPattern("HH:mm:ss, dd.MM.yyyy")

    override fun toString(): String {
        fun yesNo(mean: Boolean) = if (mean) "Да" else "Нет"
        return """Запись №$id:
            |Разместил пользователь №$fromId
            |Время публикации - ${dateTime.format(DEFAULT_TIME_FORMAT)}
            |Содержание: '$text'
            |Количество просмотров - ${viewsCount ?: 0}
            |Вам доступно удаление - ${yesNo(canDelete)}
            |Вам доступно редактирование - ${yesNo(canEdit)}
            |Владелец - пользователь №${ownerId ?: fromId}
            |Добавлена в закладки - ${yesNo(isFavourite)}
            |Количество лайков - $likes
            |""".trimMargin()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (text != other.text) return false
        if (attachments != null) {
            if (other.attachments == null) return false
            if (!attachments.contentEquals(other.attachments)) return false
        } else if (other.attachments != null) return false

        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(id, text, attachments)
    }
}