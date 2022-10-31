import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.collections.HashSet

internal class TrieTests {
    private val words = listOf("he", "she", "his", "hers")
    private val moreWords = listOf("burn", "the", "witch")
    private val random: Random = Random(23)
    private fun newTestTrie(): Trie {
        val trie: Trie = TrieImpl()
        for (word in words) {
            trie.add(word)
        }
        return trie
    }

    @Test
    fun add() {
        val trie: Trie = TrieImpl()
        words.forEach {
            assertTrue(trie.add(it))
            assertFalse(trie.add(it))
        }
        moreWords.forEach { assertTrue(trie.add(it)) }
        moreWords.forEach { assertFalse(trie.add(it)) }
    }

    @Test
    fun contains() {
        val trie: Trie = newTestTrie()
        words.forEach { assertTrue(trie.contains(it)) }
        moreWords.forEach { assertFalse(trie.contains(it)) }
    }

    @Test
    fun remove() {
        val trie: Trie = newTestTrie()
        words.forEach {
            assertTrue(trie.remove(it))
            assertFalse(trie.remove(it))
        }
        moreWords.forEach { assertFalse(trie.remove(it)) }
    }

    @Test
    fun size() {
        val trie: Trie = newTestTrie()
        var expectedSize = 4
        assertEquals(expectedSize, trie.size())
        moreWords.forEach {
            expectedSize += 1
            trie.add(it)
            assertEquals(expectedSize, trie.size())
        }
        words.forEach {
            expectedSize -= 1
            trie.remove(it)
            assertEquals(expectedSize, trie.size())
        }
        moreWords.forEach {
            trie.add(it)
            assertEquals(expectedSize, trie.size())
        }
    }

    @Test
    fun howManyStartsWithPrefix() {
        val trie: Trie = newTestTrie()
        assertEquals(trie.size(), trie.howManyStartsWithPrefix(""))
        assertEquals(3, trie.howManyStartsWithPrefix("h"))
        assertEquals(2, trie.howManyStartsWithPrefix("he"))
        assertEquals(0, trie.howManyStartsWithPrefix("hello"))
        assertEquals(0, trie.howManyStartsWithPrefix("notPresentString"))
    }

    @Test
    fun emptyString() {
        val trie: Trie = newTestTrie()
        assertFalse(trie.contains(""))
        assertTrue(trie.add(""))
        assertFalse(trie.add(""))
        assertTrue(trie.contains(""))
        assertTrue(trie.remove(""))
        assertFalse(trie.remove(""))
        assertFalse(trie.contains(""))
    }

    private fun randomString(length: Int): String {
        val array = ByteArray(length)
        random.nextBytes(array)
        return String(array, StandardCharsets.UTF_8)
    }

    @Test
    fun stressTest() {
        val poorTrie: Trie = TrieImpl()
        val inTrie: MutableSet<String> = HashSet()
        repeat(100000) {
            val wordSize: Int = random.nextInt(15) + 1
            val newWord = randomString(wordSize).strip()
            when (random.nextInt(4)) {
                0 -> {
                    poorTrie.add(newWord)
                    inTrie.add(newWord)
                }
                1 -> {
                    poorTrie.remove(newWord)
                    inTrie.remove(newWord)
                }
                2 -> poorTrie.contains(newWord)
                3 -> poorTrie.howManyStartsWithPrefix(newWord)
                else -> println("This should not happen...")
            }
        }

        // size
        assertEquals(inTrie.size, poorTrie.size())
        val deletedWords: MutableSet<String> = HashSet()
        for (inWord in inTrie) {
            // add
            assertFalse(poorTrie.add(inWord))
            // contains
            assertTrue(poorTrie.contains(inWord))
            // prefix
            assertTrue(poorTrie.howManyStartsWithPrefix(inWord) >= 1)
            // remove
            assertTrue(poorTrie.remove(inWord))
            deletedWords.add(inWord)
            if (deletedWords.size == 100) {
                break
            }
        }
        assertEquals(inTrie.size - deletedWords.size, poorTrie.size())
        for (deletedWord in deletedWords) {
            // remove
            assertFalse(poorTrie.remove(deletedWord))
            // contains
            assertFalse(poorTrie.contains(deletedWord))
            // add
            assertTrue(poorTrie.add(deletedWord))
        }
    }
}
