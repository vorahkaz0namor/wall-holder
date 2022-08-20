package ru.netology

class Likes {
    var count = 0
    var userLikes = false
    val canLike = true
    val canPublish = true

    override fun toString(): String {
        return "$count"
    }
}