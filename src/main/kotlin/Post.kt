package ru.netology.social

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Post(
    val id: Int = 0,
    val fromId: Int,
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val text: String,
    val viewsCount: Int = 0,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val ownerId: Int = 0,
    val isFavourite: Boolean = false,
    val likes: Likes = Likes()
) {
    private val DEFAULT_TIME_FORMAT =
        DateTimeFormatter.ofPattern("HH:mm:ss, dd.MM.yyyy")

    override fun toString(): String {
        return "Запись №$id (${dateTime.format(DEFAULT_TIME_FORMAT)}) " +
                "Разместил пользователь №$fromId: '$text'"
    }
}