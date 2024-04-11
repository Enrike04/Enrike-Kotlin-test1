package org.jetbrains.edu

data class TrieNode(var wordsCount: Int = 0) {
    val children = HashMap<Char, TrieNode>()
    var isEndOfWord = false
}

class TrieImpl : Trie {
    private val root = TrieNode()

    override fun add(element: String): Boolean {
        var current = root
        if (contains(element)) return false

        for (char in element) {
            if (!current.children.containsKey(char)) {
                current.children[char] = TrieNode()
            }
            current.wordsCount++
            current = current.children[char] ?: return false
        }
        current.isEndOfWord = true
        current.wordsCount++

        return true
    }

    override fun contains(element: String): Boolean {
        var current = root
        element.forEach { char ->
            if (!current.children.containsKey(char)) {
                return false
            }
            current = current.children[char] ?: return false
        }
        return current.isEndOfWord
    }

    override fun remove(element: String): Boolean {
        var current = root
        when (contains(element)) {
            true -> {
                element.forEach { char ->
                    if (!current.children.containsKey(char)) {
                        return false
                    }
                    current.wordsCount--
                    current = current.children[char] ?: return false
                }
                current.isEndOfWord = false
                current.wordsCount--

                return true
            }

            false -> return false
        }
    }

    override fun size(): Int = root.wordsCount

    override fun howManyStartsWithPrefix(prefix: String): Int {
        var current = root
        for (char in prefix) {
            if (!current.children.containsKey(char)) {
                return 0
            }
            current = current.children[char] ?: return 0
        }
        return current.wordsCount
    }
}
