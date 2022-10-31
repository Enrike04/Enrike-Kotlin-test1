### Task #1 (3 points)

Implement extension functions for `ByteArray` to compress and decompress it. 
The algorithm works according to the following rules: let's take a `ByteArray`; 
to compress it, replace the sequence of repeated bytes by two bytes - 
1) the number of duplicates, 
2) the repeated byte; to decompress it make the opposite operation.