### Task #2 (4 points)

Implement the [trie](https://en.wikipedia.org/wiki/Trie) data structure. 
You should implement the following methods:

- `boolean add(String element)` - Returns true if the element was added the first time, asymptotic complexity `O(|element|))`
- `boolean contains(String element)` - Checks whether trie has the element, asymptotic complexity `O(|element|)`
- `boolean remove(String element)` - Removes the element from the trie, returns true if the element was actually in the trie, asymptotic complexity `O(|element|)`
- `int size()` - Returns the number of strings in the trie, asymptotic complexity `O(1)`
- `int howManyStartWithPrefix(String prefix)` - Returns the number of strings that start with the given prefix, asymptotic complexity `O(|prefix|)`
