package ru.netology

import java.lang.StringBuilder

class PostNotFoundException(message: String) : ArrayIndexOutOfBoundsException(message)
class CommentNotFoundException(message: String) : ArrayIndexOutOfBoundsException(message)
class ReasonNotFoundException(message: String) : ArrayIndexOutOfBoundsException(message)
class InvalidClassException(message: String) : ClassCastException(message)

object WallService {
    private var postArray = emptyArray<Post>()
    private var commentsArray = emptyArray<Comment>()
    private var complaintsToComment = emptyArray<ComplaintToComment>()
    private val complaintReasons: HashMap<Int, String> =
        HashMap(mapOf(
            Pair(0, "спам"),
            Pair(1, "детская порнография"),
            Pair(2, "экстремизм"),
            Pair(3, "насилие"),
            Pair(4, "пропаганда наркотиков"),
            Pair(5, "материал для взрослых"),
            Pair(6, "оскорбление"),
            Pair(7, "призывы к суициду")
        ))
    private var postNewId = 0
    private var commentNewId = 0

    fun getPostNewId(): Int {
        return postNewId
    }

    fun getCommentNewId(): Int {
        return commentNewId
    }

    fun getComplaintsArraySize(): Int {
        return complaintsToComment.size
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

    fun addComplaintToComment(comment: Comment, reasonId: Int): ComplaintToComment {
        if (commentsArray.getOrNull(comment.id) == null)
            throw CommentNotFoundException("Отсутствует комментарий с номером №${comment.id}")
        if (reasonId !in 0..7)
            throw ReasonNotFoundException("""
                |Для комментария №${comment.id} некорректно указана причина жалобы, 
                |либо указанная причина не классифицируется как жалоба.
                |""".trimMargin())
        val complaint = ComplaintToComment(comment.fromId, comment.id, complaintReasons[reasonId])
        complaintsToComment += complaint
        return complaint
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