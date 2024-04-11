package org.jetbrains.edu

fun ByteArray.zip(): ByteArray {
    if (this.isEmpty()) return ByteArray(0)

    val result = mutableListOf<Byte>()
    var currentByte = this[0]
    var count = 1

    for (i in 1 until this.size) {
        if (this[i] == currentByte && count < 255) {
            count++
        } else {
            result.add(count.toByte())
            result.add(currentByte)
            currentByte = this[i]
            count = 1
        }
    }

    result.add(count.toByte())
    result.add(currentByte)

    return result.toByteArray()
}

fun ByteArray.unZip(): ByteArray {
    if (this.isEmpty()) return ByteArray(0)

    val result = mutableListOf<Byte>()

    for (i in indices step (2)) {
        val count = this[i].toInt() and 0xFF
        val byte = this[i + 1]
        repeat(count) {
            result.add(byte)
        }
    }
    return result.toByteArray()
}
