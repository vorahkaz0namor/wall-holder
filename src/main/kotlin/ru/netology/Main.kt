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
    val commentSecond = Comment(fromId = 8)
    Thread.sleep(1000)
    println(WallService.addComment(postI.id, commentFirst))
    Thread.sleep(1000)
    WallService.addComment(postIIUpdate.id, commentSecond)
    WallService.addAttachment(photo, WallService.getComment(1))
    print(WallService.getComment(1))

    println("ДОБАВЛЕНИЕ ЖАЛОБ К КОММЕНТАРИЯМ...\n")
    Thread.sleep(1000)
    val commentThird = Comment(fromId = 900, text = """
        |ТОЛЬКО СЕГОДНЯ!!!
        |Ипотека по выгодной ставке - от 5% годовых!
        """.trimMargin())
    WallService.addComment(postI.id, commentThird)
    println(WallService.getComment(2))
    print(WallService.addComplaintToComment(WallService.getComment(2), 0))
}