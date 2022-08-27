package ru.netology

import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun addPost() {
        WallService.addPost(Post(fromId = 2, text = "Zero"))
        assertTrue(WallService.getPostNewId() != 0)
    }

    @Test
    fun addComment() {
        WallService.addComment(0, Comment(fromId = 2))
        assertTrue(WallService.getCommentNewId() != 0)
    }

    @Test(expected = PostNotFoundException::class)
    fun postNotFoundException() {
        WallService.addComment(88, Comment(fromId = 2))
    }

    @Test(expected = InvalidClassException::class)
    fun invalidClassException() {
        WallService.addAttachment(File(), "Comment")
    }

    @Test
    fun addAttachment() {
        val post = Post(fromId = 3, text = "Null")
        WallService.addPost(post)
        WallService.addAttachment(Link(), post)
        assertTrue(post.attachmentId != 0)
    }

    @Test
    fun updatePostTrue() {
        assertTrue(WallService.updatePost(Post(id = 0, fromId = 3, text = "Last = True")))
    }

    @Test
    fun updatePostFalse() {
        assertFalse(WallService.updatePost(Post(id = 2, fromId = 3, text = "Last = False")))
    }
}