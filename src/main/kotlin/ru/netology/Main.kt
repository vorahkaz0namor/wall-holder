package ru.netology

fun main() {
    val postI = Post(fromId = 3, text = "Всем привет!")
    val postIIUpdate = Post(id = 1, fromId = 7, text = "Нет, от меня - Всем добра!")
    val audioTrack = Audio()
    val photo = Photo()
    val commentFirst = Comment(fromId = 7, text = "Как дела?")

    println("ДОБАВЛЕНИЕ ПОСТОВ НА СТЕНУ...\n")
    WallService.addPost(postI)
    Thread.sleep(1000)
    val postII = Post(fromId = 7, text = "И от меня - Привет!")
    WallService.addPost(postII)
    print(WallService)

    println("ОБНОВЛЕНИЕ ПОСТА №${postIIUpdate.id + 1}...\n")
    WallService.updatePost(postIIUpdate)
    WallService.addAttachment(audioTrack, WallService.getPost(1))
    Thread.sleep(1000)
    print(WallService.getPost(1))

    println("ДОБАВЛЕНИЕ КОММЕНТАРИЕВ К ПОСТАМ...\n")
    Thread.sleep(1000)
    println(WallService.addComment(postI.id, commentFirst))
    Thread.sleep(1000)
    val commentSecond = Comment(fromId = 8)
    WallService.addComment(postIIUpdate.id, commentSecond)
    WallService.addAttachment(photo, WallService.getComment(1))
    print(WallService.getComment(1))
    Thread.sleep(1000)
    println(WallService.addComment(87, commentSecond))
}