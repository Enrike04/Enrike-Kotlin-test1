package org.jetbrains.edu

fun main() {
    val trie: Trie = TrieImpl()
    assert(trie.add("her"))
    assert(trie.add("hero"))
    assert(trie.add("hers"))
    assert(trie.add("hereafter"))
    assert(!trie.add("her"))
    assert(trie.howManyStartsWithPrefix("her") == 4)
    assert(trie.remove("her"))
    assert(!trie.remove("her"))
    assert(trie.howManyStartsWithPrefix("her") == 3)
}
