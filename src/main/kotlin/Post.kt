package ru.netology.social

import java.time.LocalDateTime

data class Post(
    val id: Int,
    val fromId: Int,
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val text: String = "Напишите что-нибудь...",
    val viewsCount: Int = 0,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val ownerId: Int,
    val isFavourite: Boolean = false,
    val likes: Likes = Likes()
)
