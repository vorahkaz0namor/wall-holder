package ru.netology

fun main() {
    val postI = Post(fromId = 3, text = "Всем привет!")
    Thread.sleep(1000)
    val postII = Post(fromId = 7, text = "И от меня - Привет!")
    val postIIUpdate = Post(id = 1, fromId = 7, text = "Нет, от меня - Всем добра!")
    val audioTrack = Audio()

    println("ДОБАВЛЕНИЕ ПОСТОВ НА СТЕНУ...\n")
    WallService.addPost(postI)
    WallService.addPost(postII)
    print(WallService)

    println("ОБНОВЛЕНИЕ ПОСТА №${postIIUpdate.id + 1}...\n")
    WallService.updatePost(postIIUpdate)
    WallService.addAttachment(audioTrack, WallService.getPost(1))
    print(WallService.getPost(1))
}