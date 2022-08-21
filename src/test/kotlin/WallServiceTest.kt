package ru.netology

import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun addPost() {
        WallService.addPost(Post(fromId = 2, text = "Zero"))
        assertTrue(WallService.getNewId() != 0)
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