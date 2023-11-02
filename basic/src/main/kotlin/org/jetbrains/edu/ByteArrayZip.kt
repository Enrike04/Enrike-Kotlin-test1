package org.jetbrains.edu

private fun MutableList<Byte>.safeAdd(byte: Byte, count: Int) {
    var mutableCount = count
    while (mutableCount > 0) {
        val step = minOf(UByte.MAX_VALUE.toInt(), mutableCount)
        add(step.toUByte().toByte())
        add(byte)
        mutableCount -= step
    }
}

fun ByteArray.zip(): ByteArray = buildList {
    if (this@zip.isEmpty()) return@buildList
    var count = 1
    var lastByte = this@zip.first()
    for (byte in this@zip.drop(1)) {
        if (byte == lastByte) {
            count++
        } else {
            safeAdd(lastByte, count)
            lastByte = byte
            count = 1
        }
    }
    safeAdd(lastByte, count)
}.toByteArray()

private operator fun List<Byte>.component1() = first()
private operator fun List<Byte>.component2() = last()

fun ByteArray.unZip(): ByteArray = buildList {
    this@unZip.toList()
        .chunked(2)
        .forEach { (count, byte) -> repeat(count.toUByte().toInt()) { add(byte) } }
}.toByteArray()
