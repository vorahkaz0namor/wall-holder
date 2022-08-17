package ru.netology.social

fun main() {
    val postI = Post(fromId = 3, text = "Всем привет!")
    Thread.sleep(1000)
    val postII = Post(fromId = 7, text = "И от меня - Привет!")
    val postIUpdate = Post(id = 1, fromId = 7, text = "Нет, от меня - Всем добра!")

    WallService.addPost(postI)
    WallService.addPost(postII)
    println(WallService)
    WallService.updatePost(postIUpdate)
    println(WallService)
}