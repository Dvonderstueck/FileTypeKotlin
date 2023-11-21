fun main() {
    val fileAnalyzer = FileAnalyzer()

    print("Enter the path to the file: ")
    val filePath = readLine()

    fileAnalyzer.analyzeFile(filePath)
}