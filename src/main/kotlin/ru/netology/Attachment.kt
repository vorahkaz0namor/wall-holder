package ru.netology

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val DEFAULT_DATE_FORMAT =
    DateTimeFormatter.ofPattern("dd.MM.yyyy")

sealed class Attachment {
    val type: String
        get() = when (this) {
            is Audio -> "Audio"
            is Photo -> "Photo"
            is Video -> "Video"
            is File -> "File"
            is Link -> "Link"
        }
}

data class Audio(
    val id: Int = 0,
    val ownerId: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val artist: String = "Исполнитель",
    val title: String = "Название песни",
    val url: String = "https://"
) : Attachment() {
    override fun toString() =
        "№${id + 1} - трек: $artist - $title (${date.format(DEFAULT_DATE_FORMAT)})"
}

data class Photo(
    val id: Int = 0,
    val ownerId: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val text: String = "Фотография",
    val width: Int? = null,
    val height: Int? = null
) : Attachment() {
    override fun toString() =
        "№${id + 1} - фото: $text (${date.format(DEFAULT_DATE_FORMAT)})"
}

data class Video(
    val id: Int = 0,
    val ownerId: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val title: String = "Видеоролик",
    val description: String? = null,
    val comments: Int? = null
) : Attachment() {
    override fun toString() =
        "№${id + 1} - видео: $title (${date.format(DEFAULT_DATE_FORMAT)})"
}

data class File(
    val id: Int = 0,
    val ownerId: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val title: String = "Имя файла",
    val ext: String? = null,
    val url: String = "https://"
) : Attachment() {
    override fun toString() =
        "№${id + 1} - файл: $title.${if (ext != null) ".$ext" else ""} (${date.format(DEFAULT_DATE_FORMAT)})"
}

data class Link(
    val id: Int = 0,
    val url: String = "https://",
    val title: String = "Ссылка",
    val description: String? = null,
) : Attachment() {
    override fun toString() =
        "№${id + 1} - ссылка: $title '$url'"
}