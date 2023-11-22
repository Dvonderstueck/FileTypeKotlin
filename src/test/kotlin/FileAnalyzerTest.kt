import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class FileAnalyzerTest {

    @Test
    fun testFindFileTypeWithPNG() {
        val fileAnalyzer = FileAnalyzer()
        val fileHeader = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte())
        val fileType = fileAnalyzer.findFileType(fileHeader)

        assertEquals("PNG", fileType)
    }

    @Test
    fun testFindFileTypeWithJPEG() {
        val fileAnalyzer = FileAnalyzer()
        val fileHeader = byteArrayOf(0xFF.toByte(), 0xD8.toByte(), 0xFF.toByte())
        val fileType = fileAnalyzer.findFileType(fileHeader)

        assertEquals("JPEG", fileType)
    }

    @Test
    fun testFindFileTypeWithUnknownType() {
        val fileAnalyzer = FileAnalyzer()
        val fileHeader = byteArrayOf(0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte())

        try {
            fileAnalyzer.findFileType(fileHeader)
            fail("Expected IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertEquals("Unknown file type", e.message)
        }
    }
}
