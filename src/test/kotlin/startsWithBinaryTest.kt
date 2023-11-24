import org.junit.Test
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class TestStartsWithBinary {

    fun startsWithBinary(fileHeader: ByteArray, magicFile: ByteArray): Boolean {
        if (magicFile.size > fileHeader.size) {
            return false
        }

        return fileHeader.copyOfRange(0, magicFile.size).contentEquals(magicFile)
    }


    @Test
    fun testStartsWithBinaryTrue() {
        val fileHeader: ByteArray = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte(), 0xFF.toByte(), 0xD8.toByte(), 0xFF.toByte())
        val magicFile: ByteArray = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte())
        val result: Boolean = startsWithBinary(fileHeader, magicFile)
        assertTrue(result)
    }

    @Test
    fun testStartsWithBinaryFalse() {
        val fileHeader: ByteArray = byteArrayOf(0xFF.toByte(), 0xD8.toByte(), 0xFF.toByte(), 0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte())
        val magicFile: ByteArray = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte())
        val result: Boolean = startsWithBinary(fileHeader, magicFile)
        assertFalse(result)
    }

    @Test
    fun testStartsWithBinaryShort() {
        val fileHeader: ByteArray = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte())
        val magicFile: ByteArray = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte())
        val result: Boolean = startsWithBinary(fileHeader, magicFile)
        assertFalse(result)
    }

    @Test
    fun testStartsWithBinaryEmptyString() {
        val fileHeader: ByteArray = byteArrayOf()
        val magicFile: ByteArray = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte())
        val result: Boolean = startsWithBinary(fileHeader, magicFile)
        assertFalse(result)
    }
    @Test
    fun testStartsWithBinaryLongSlice() {
        val fileHeader: ByteArray = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte())
        val magicFile: ByteArray = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte(), 0xFF.toByte(), 0xD8.toByte(), 0xFF.toByte())

        val result: ByteArray = fileHeader.copyOfRange(0, fileHeader.size)
        assertArrayEquals( result, magicFile.copyOfRange(0, result.size))
    }


}

