package ru.netology

data class Likes(
    var count: Int = 0,
    var userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
) { override fun toString() = "$count" }