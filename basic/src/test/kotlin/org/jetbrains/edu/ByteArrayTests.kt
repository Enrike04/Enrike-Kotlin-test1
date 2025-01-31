package org.jetbrains.edu

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.opentest4j.AssertionFailedError

internal class ByteArrayTests {

    companion object {
        @JvmStatic
        fun zipData() = listOf(
            Arguments.of(
                ByteArray(16) { it.toByte() },
                ByteArray(32) { if (it % 2 == 0) 1.toByte() else (it / 2).toByte() }),
            Arguments.of(
                byteArrayOf(
                    Byte.MAX_VALUE,
                    Byte.MAX_VALUE,
                    Byte.MAX_VALUE,
                    Byte.MIN_VALUE,
                    Byte.MIN_VALUE,
                    Byte.MIN_VALUE,
                ),
                byteArrayOf(3.toByte(), Byte.MAX_VALUE, 3.toByte(), Byte.MIN_VALUE),
            ),
            Arguments.of(byteArrayOf(10), byteArrayOf(1, 10)),
            Arguments.of(ByteArray(UByte.MAX_VALUE.toInt()) { 20 }, byteArrayOf(UByte.MAX_VALUE.toByte(), 20)),
            Arguments.of(
                ByteArray(UByte.MAX_VALUE.toInt() + 1) { 20.toByte() },
                byteArrayOf(UByte.MAX_VALUE.toByte(), 20, 1, 20),
            ),
        )

        @JvmStatic
        fun zipUnzipData() = listOf(
            Arguments.of(ByteArray(16) { it.toByte() }),
            Arguments.of(ByteArray(100) { Byte.MAX_VALUE }.also {
                for (i in it.lastIndex / 2..it.lastIndex) {
                    it[i] = Byte.MIN_VALUE
                }
            }),
            Arguments.of(ByteArray((100..10000).random()) { (Byte.MIN_VALUE..Byte.MAX_VALUE).random().toByte() }),
            Arguments.of(ByteArray((100..10000).random()) { (Byte.MIN_VALUE..Byte.MAX_VALUE).random().toByte() }),
            Arguments.of(ByteArray(1) { 10.toByte() }),
            Arguments.of(ByteArray(UByte.MAX_VALUE.toInt()) { 20.toByte() })
        )

        @JvmStatic
        fun sizeOfZipData() = listOf(
            Arguments.of(ByteArray(UByte.MAX_VALUE.toInt()) { 20.toByte() }, 2),
            Arguments.of(ByteArray(UByte.MAX_VALUE.toInt() + 1) { 20.toByte() }, 4)
        )
    }

    @ParameterizedTest
    @MethodSource("zipUnzipData")
    fun zipUnzipCorrectTest(array: ByteArray) {
        assertArrayEquals(array, array.zip().unZip())
        assertThrows<AssertionFailedError> { assertArrayEquals(array, array.zip()) }
    }


    @ParameterizedTest
    @MethodSource("zipData")
    fun zipCorrectTest(array: ByteArray, expectedArray: ByteArray) {
        assertArrayEquals(array.zip(), expectedArray)
    }


    @Test
    fun zipUnzipEmptyTest() {
        val array = emptyList<Byte>().toByteArray()
        assertArrayEquals(array, array.zip().unZip())
    }

    @ParameterizedTest
    @MethodSource("sizeOfZipData")
    fun sizeOfZipTest(array: ByteArray, expectedSize: Int) {
        assertEquals(expectedSize, array.zip().size)
    }
}
