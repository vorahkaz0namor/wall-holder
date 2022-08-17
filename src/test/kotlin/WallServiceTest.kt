package ru.netology.social

import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun addPost() {
        WallService.addPost(Post(fromId = 2, text = "Zero"))
        assertTrue(WallService.getNewId() != 0)
    }

    @Test
    fun updatePost() {
        val texts = listOf("One", "Two", "Three", "Four", "Five")
        for (t in texts)
            WallService.addPost(Post(fromId = 3, text = t))
        assertTrue(WallService.updatePost(Post(id = 5, fromId = 3, text = "Last = True")))
        assertFalse(WallService.updatePost(Post(id = 6, fromId = 3, text = "Last = False")))
    }
}