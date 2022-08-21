package ru.netology

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val DEFAULT_DATE_FORMAT =
    DateTimeFormatter.ofPattern("dd.MM.yyyy")

abstract class AudioAttachment : Attachment {
    override val type = "Audio"
}

data class Audio(
    val id: Int = 0,
    val ownerId: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val artist: String = "Исполнитель",
    val title: String = "Название песни",
    val url: String = "https://"
) : AudioAttachment() {
    override fun toString() =
        "№${id + 1} - трек: $artist - $title (${date.format(DEFAULT_DATE_FORMAT)})"
}

abstract class PhotoAttachment : Attachment {
    override val type: String = "Photo"
}

data class Photo(
    val id: Int = 0,
    val ownerId: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val text: String = "Фотография",
    val width: Int? = null,
    val height: Int? = null
) : PhotoAttachment() {
    override fun toString() =
        "№${id + 1} - фото: $text (${date.format(DEFAULT_DATE_FORMAT)})"
}

abstract class VideoAttachment : Attachment {
    override val type: String = "Video"
}

data class Video(
    val id: Int = 0,
    val ownerId: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val title: String = "Видеоролик",
    val description: String? = null,
    val comments: Int? = null
) : VideoAttachment() {
    override fun toString() =
        "№${id + 1} - видео: $title (${date.format(DEFAULT_DATE_FORMAT)})"
}

abstract class FileAttachment : Attachment {
    override val type: String = "File"
}

data class File(
    val id: Int = 0,
    val ownerId: Int? = null,
    val date: LocalDate = LocalDate.now(),
    val title: String = "Имя файла",
    val ext: String? = null,
    val url: String = "https://"
) : FileAttachment() {
    override fun toString() =
        "№${id + 1} - файл: $title.${if (ext != null) ".$ext" else ""} (${date.format(DEFAULT_DATE_FORMAT)})"
}

abstract class LinkAttachment : Attachment {
    override val type: String = "Link"
}

data class Link(
    val id: Int = 0,
    val url: String = "https://",
    val title: String = "Ссылка",
    val description: String? = null,
) : LinkAttachment() {
    override fun toString() =
        "№${id + 1} - ссылка: $title '$url'"
}