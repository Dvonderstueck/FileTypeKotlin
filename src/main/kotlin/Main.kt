import java.io.File

fun readHeader(path: String): ByteArray {
    return File(path).readBytes()
}

fun startsWithBinary(fileHeader: ByteArray, magicFile: ByteArray): Boolean {
    return fileHeader.copyOfRange(0, magicFile.size).contentEquals(magicFile)
}

fun findFileType(header: ByteArray): String {
    val magicNumbers = mapOf(
        byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte()) to "PNG",
        byteArrayOf(0xFF.toByte(), 0xD8.toByte(), 0xFF.toByte()) to "JPEG"
    )

    for ((magic, fileType) in magicNumbers) {
        if (startsWithBinary(header, magic)) {
            return fileType
        }
    }
    throw IllegalArgumentException("Unknown file type")
}

fun main() {
    print("Enter the path to the file: ")
    val filePath = readLine()

    try {
        if (filePath != null) {
            val header = readHeader(filePath)
            val result = findFileType(header)
            println("File $filePath is of type $result")
        } else {
            println("Invalid file path.")
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
