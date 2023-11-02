### Task #1, Basic Kotlin

Implement extension functions for `ByteArray` to compress and decompress it. 
To compress a `ByteArray`, replace the sequence of repeated bytes with two bytes: the number of duplicates and the 
repeated byte. To decompress it, reverse the operation. 

This operation should be done for all cases, including cases when we have only one repeated byte.
The main goal of this task is not to implement an efficient compression algorithm, but to apply basic Kotlin knowledge 
to write a simple array transformation.


## Examples

1. Basic random array

Input array: `0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15`
Zipped array: `1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9, 1, 10, 1, 11, 1, 12, 1, 13, 1, 14, 1, 15`

2. An array with `Byte.MAX_VALUE` and `Byte.MIN_VALUE`

Input array: `127 127 127 -128 -128 -128`
Zipped array: `3 127 3 -128`

3. An array with one item

Input array: `10`
Zipped array: `1, 10`

4. An array with 255 (`UByte.MAX_VALUE.toInt()`) items:

Input array: `20, 20, 20, ....`
Zipped array: `-1, 20`

5. An array with 256 (`UByte.MAX_VALUE.toInt() + 1`) items:

Input array: `20, 20, 20, ..., 20`
Zipped array: `-1, 20, 1, 20`
