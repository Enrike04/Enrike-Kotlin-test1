### Task #1, Basic Kotlin

Implement extension functions for `ByteArray` to compress and decompress it. 
To compress a `ByteArray`, replace the sequence of repeated bytes with two bytes: the number of duplicates and the 
repeated byte. To decompress it, reverse the operation. 

Examples:
- `ByteArray(AAABCBC) -> ByteArray(A3B1C1B1C1)`
- `ByteArray(AAAABCCCCD) -> ByteArray(A4B1C4D1)`
- `ByteArray(AA...AABC)` with 300 consecutive As `-> ByteArray(A255A45B1C1)`
- `ByteArray() -> ByteArray()` (empty case)
