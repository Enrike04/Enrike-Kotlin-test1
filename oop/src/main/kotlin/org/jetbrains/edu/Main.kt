package org.jetbrains.edu

fun main() {
    val trie = TrieImpl()
    trie.add("her")
    trie.add("hero")
    trie.add("hers")
    trie.add("hereafter")
    trie.printAllPaths()
    trie.remove("hereafter")
    trie.printAllPaths()
}
