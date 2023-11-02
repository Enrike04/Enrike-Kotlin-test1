interface Trie {
    fun add(element: String): Boolean

    fun contains(element: String): Boolean

    fun remove(element: String): Boolean

    fun size(): Int

    fun howManyStartsWithPrefix(prefix: String): Int
}
