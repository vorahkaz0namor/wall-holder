package ru.netology

data class ComplaintToComment(
    val ownerId: Int,
    val commentId: Int,
    val reason: String?
) {
    override fun toString(): String {
        return """
            |Жалоба на комментарий №${commentId + 1}:
            |"$reason"
            |""".trimMargin()
    }
}
