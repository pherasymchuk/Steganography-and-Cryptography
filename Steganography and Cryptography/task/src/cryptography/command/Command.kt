package cryptography.command

import cryptography.io.ImageIOHandler
import cryptography.io.Reader
import cryptography.util.Console
import cryptography.util.Steganography
import kotlin.system.exitProcess

interface Command {
    fun execute()

    class HideCommand(reader: Reader) : Command {
        private val imageIOHandler: ImageIOHandler = ImageIOHandler()
        private val steganography: Steganography = Steganography()
        private val console: Console = Console(reader)

        override fun execute() {
            val inputFile = console.getInputFileFromUser()
            val outputFile = console.getOutputFileFromUser()

            if (!inputFile.exists()) return println("Can't read input file!")

            println("Message to hide:")
            val messageBytesWithTerminator: ByteArray = console.getMessageWithTerminator()
            val image = imageIOHandler.readImage(inputFile)
            if (!steganography.canHideMessage(image, messageBytesWithTerminator)) {
                return println("The input image is not large enough to hold this message.")
            }
            steganography.hideMessage(image, messageBytesWithTerminator)
            imageIOHandler.writeImage(image, outputFile)
            println("Message saved in $outputFile image.")
        }
    }

    class ShowCommand(reader: Reader) : Command {
        private val console: Console = Console(reader)
        private val steganography = Steganography()
        private val imageIOHandler = ImageIOHandler()

        override fun execute() {
            val inputFile = console.getInputFileFromUser()
            if (!inputFile.exists()) return println("Can't read input file!")
            val message = steganography.extractMessage(imageIOHandler.readImage(inputFile))
            println("Message: \n$message")
        }
    }

    class ExitCommand : Command {
        override fun execute() {
            println("Bye!")
            exitProcess(0)
        }
    }
}
