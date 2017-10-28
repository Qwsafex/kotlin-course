package ru.spbau.mit



class Fenwick(private val size: Int) {
    private val arr: Array<Int> = Array(size, {0})

    fun add(pos: Int, value: Int) {
        var i = pos
        while (i < size) {
            arr[i] += value
            i = i or i + 1
        }
    }

    fun getSum(to: Int): Int {
        var sum = 0
        var r = to
        while (r >= 0) {
            sum += arr[r]
            r = (r and r + 1) - 1
        }
        return sum
    }
}

class Nickname(private val originalNickname: String) {
    companion object {
        private val ALPHABET_SIZE: Int = 26
    }

    private val deletedLetters: Array<Boolean> = Array(originalNickname.length, {false})
    private val letterCount: Array<Fenwick> = Array(ALPHABET_SIZE, {Fenwick(originalNickname.length)})

    init {
        for (i in 0 until originalNickname.length) {
            letterCount[originalNickname[i].alphabetPos()].add(i, 1)
        }
    }

    private fun Char.alphabetPos(): Int = this - 'a'

    fun get(): String {
        val currentNickname = StringBuilder()
        for (i in 0 until originalNickname.length) {
            if (!deletedLetters[i]) {
                currentNickname.append(originalNickname[i])
            }
        }
        return currentNickname.toString()
    }

    fun deleteNthLetter(n: Int, letter: Char) {
        var left = -1
        var right = originalNickname.length - 1
        val thisLetterCount = letterCount[letter.alphabetPos()]
        while (left < right - 1) {
            val mid = (left + right) / 2
            if (thisLetterCount.getSum(mid) >= n) right = mid
            else left = mid
        }
        deletedLetters[right] = true
        thisLetterCount.add(right, -1)
    }
}

fun main(args: Array<String>) {
    val repeatCount = readLine()!!.toInt()
    val nicknamePart = readLine()
    val modificationCount = readLine()!!.toInt()
    val nickname = Nickname(nicknamePart!!.repeat(repeatCount))
    for (i in 0 until modificationCount) {
        val (pos, letter) = readLine()!!.split(' ')
        nickname.deleteNthLetter(pos.toInt(), letter[0])
    }
    print(nickname.get())
}
