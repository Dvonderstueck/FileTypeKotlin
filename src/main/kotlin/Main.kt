fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please provide the path to the file as a command-line argument.")
    } else {
        val filePath = args[0]
        val fileAnalyzer = FileAnalyzer()
        fileAnalyzer.analyzeFile(filePath)
    }
}