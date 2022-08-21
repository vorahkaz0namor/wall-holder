package ru.netology

import java.lang.StringBuilder

object WallService {
    private var postArray = emptyArray<Post>()
    private var newId = 0

    fun getNewId(): Int {
        return newId
    }

    fun getPost(postId: Int): Post {
        return postArray[postId]
    }

    fun addPost(post: Post): Post {
        val thisId = newId++
        postArray += post.copy(id = thisId)
        return postArray[thisId]
    }

    fun addAttachment(attachment: Attachment, post: Post) {
        val thisId = post.attachmentId++
        val addAttachment =
            when (attachment.type) {
                "Audio" -> {
                    attachment as Audio
                    attachment.copy(id = thisId)
                }
                "Photo" -> {
                    attachment as Photo
                    attachment.copy(id = thisId)
                }
                "Video" -> {
                    attachment as Video
                    attachment.copy(id = thisId)
                }
                "File" -> {
                    attachment as File
                    attachment.copy(id = thisId)
                }
                else -> {
                    attachment as Link
                    attachment.copy(id = thisId)
                }
            }
        postArray[post.id] = postArray[post.id].copy(
            attachments = postArray[post.id].attachments + addAttachment
        )
    }

    fun updatePost(post: Post): Boolean {
        if (postArray.getOrNull(post.id) == null)
            return false
        postArray[post.id] = postArray[post.id].copy(
            text = post.text,
            viewsCount = post.viewsCount,
            canDelete = post.canDelete,
            canEdit = post.canEdit,
            ownerId = post.ownerId,
            isFavourite = post.isFavourite,
            likes = post.likes,
            attachments = post.attachments
        )
        return true
    }

    override fun toString(): String {
        val wallToString = StringBuilder()
        for (p in postArray)
            wallToString.append(p).append("\n")
        return "С Т Е Н А :\n$wallToString"
    }
}