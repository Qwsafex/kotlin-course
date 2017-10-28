package ru.spbau.mit

import org.junit.Test

import org.junit.Assert.*

class NicknameTest {
    companion object {
        private val STUB_NICKNAME: String = "fedorivanovich"
    }

    @Test
    fun doesntChange_whenNoModifications() {
        val nickname = Nickname(STUB_NICKNAME)
        assertEquals(STUB_NICKNAME, nickname.get())
    }

    @Test
    fun test1fromCf() {
        val nickname = Nickname("bac".repeat(2))
        nickname.deleteNthLetter(2, 'a')
        nickname.deleteNthLetter(1, 'b')
        nickname.deleteNthLetter(2, 'c')
    }

    @Test
    fun test2fromCf() {
        val nickname = Nickname("abacaba")
        nickname.deleteNthLetter(1, 'a')
        nickname.deleteNthLetter(1, 'a')
        nickname.deleteNthLetter(1, 'c')
        nickname.deleteNthLetter(2, 'b')
    }
}