package org.jetbrains.edu

class TrieImpl : Trie {
    // private val root = this
    private var isTerminal: Boolean = false
    private val children: MutableMap<Char, TrieImpl> = mutableMapOf()
    private var size = 0

    private fun getOrPut(char: Char) = children.getOrPut(char) { TrieImpl() }

    private fun getOrNull(char: Char) = children.get(char)

    private fun followWord(
        element: String,
        how: TrieImpl.(Char) -> TrieImpl? = { getOrNull(it) }
    ) =
        element.fold(mutableListOf<TrieImpl?>(this)) { trieList, char ->
            val next = trieList.last()?.how(char)
            trieList.add(next)
            trieList
        }

    override fun add(element: String): Boolean {
        val path = followWord(element) { getOrPut(it) }.filterNotNull()
        if (path.last().isTerminal) {
            return false
        }
        path.last().isTerminal = true
        path.forEach { it.size++ }
        return true
    }

    override fun contains(element: String) = followWord(element).lastOrNull()?.isTerminal ?: false

    override fun remove(element: String): Boolean {
        val path = followWord(element)
        val last = path.lastOrNull()
        if (last == null || !last.isTerminal) {
            return false
        }
        for ((index, trie) in path.filterNotNull().withIndex()) {
            trie.size--
            if (trie.size == 0 && index > 0) {
                path[index - 1]?.children?.remove(element[index - 1])
                break
            }
        }
        last.isTerminal = false
        return true
    }

    override fun size() = size

    override fun howManyStartsWithPrefix(prefix: String): Int {
        val lastNode = followWord(prefix).last() ?: return 0
        return lastNode.size
    }

    fun printAllPaths(depth: Int = 0) {
        if (children.isEmpty()) {
            println("")
            return
        }
        if (children.size == 1) {
            print(children.keys.first())
            children.values.first().printAllPaths(depth + 1)
            return
        }
        println("-")
        children.forEach { (char, trie) ->
            repeat(depth) { print("\t") }
            print(char)
            trie.printAllPaths(depth + 1)
        }
    }
}
