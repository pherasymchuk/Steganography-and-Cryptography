package cryptography.command

import cryptography.io.ImageIOHandler
import cryptography.util.Console
import cryptography.util.ImageHider

class HideCommand : Command {
    private val imageIOHandler: ImageIOHandler = ImageIOHandler()
    private val imageHider: ImageHider = ImageHider()

    override fun execute() {
        val inputFile = Console.getInputFileFromUser()
        val outputFile = Console.getOutputFileFromUser()

        if (!inputFile.exists()) return println("Can't read input file!")

        println(
            """
            Input Image: $inputFile
            Output Image: $outputFile
        """.trimIndent()
        )

        val image = imageIOHandler.readImage(inputFile)
        imageHider.hideImage(image)
        imageIOHandler.writeImage(image, outputFile)
        println("Image $outputFile is saved.")
    }
}
