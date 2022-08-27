package ru.netology

import java.lang.StringBuilder

class PostNotFoundException(message: String) : ArrayIndexOutOfBoundsException(message)
class InvalidClassException(message: String) : ClassCastException(message)

object WallService {
    private var postArray = emptyArray<Post>()
    private var commentsArray = emptyArray<Comment>()
    private var postNewId = 0
    private var commentNewId = 0

    fun getPostNewId(): Int {
        return postNewId
    }

    fun getCommentNewId(): Int {
        return commentNewId
    }

    fun getPost(postId: Int): Post {
        return postArray[postId]
    }

    fun getComment(commentId: Int): Comment {
        return commentsArray[commentId]
    }

    fun addPost(post: Post): Post {
        val thisId = postNewId++
        postArray += post.copy(id = thisId)
        return postArray[thisId]
    }

    fun addComment(postId: Int, comment: Comment): Comment {
        if (postArray.getOrNull(postId) == null)
            throw PostNotFoundException("Отсутствует пост с номером №$postId")
        val thisId = commentNewId++
        commentsArray += comment.copy(id = thisId, toPostId = postId)
        return commentsArray[thisId]
    }

    fun addAttachment(attachment: Attachment, obj: Any) {
        val thisId =
            when (obj) {
                is Post -> obj.attachmentId++
                is Comment -> obj.attachmentId++
                else -> {
                    throw InvalidClassException("Целевой объект не является Постом или Комментарием")
                }
            }
        val addAttachment =
            when (attachment) {
                is Audio -> attachment.copy(id = thisId)
                is Photo -> attachment.copy(id = thisId)
                is Video -> attachment.copy(id = thisId)
                is File -> attachment.copy(id = thisId)
                is Link -> attachment.copy(id = thisId)
            }
        when (obj) {
            is Post -> postArray[obj.id] = postArray[obj.id].copy(
                attachments = postArray[obj.id].attachments + addAttachment
            )
            is Comment -> commentsArray[obj.id] = commentsArray[obj.id].copy(
                attachments = commentsArray[obj.id].attachments + addAttachment
            )
        }
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

    fun allAttachmentsToString(attachments: Array<Attachment>): String {
        val attachmentsToString = StringBuilder()
        for (att in attachments)
            attachmentsToString.append(att).append("\n   ")
        return """Вложения:
            |   ${if (attachments.isEmpty()) "отсутствуют" else attachmentsToString}
            |""".trimMargin()
    }
}