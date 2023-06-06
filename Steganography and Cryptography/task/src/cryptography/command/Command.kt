package cryptography.command

import cryptography.util.Console
import cryptography.util.ImageUtil
import cryptography.util.MessageEncryptor
import cryptography.util.toBitList
import kotlin.system.exitProcess

interface Command {
    fun execute()

    class HideCommand : Command {
        private val console: Console = Console()
        private val imageUtil: ImageUtil = ImageUtil()
        private val encryptor: MessageEncryptor = MessageEncryptor()

        override fun execute() {
            val inputFile = console.getInputFileFromUser()
            val outputFile = console.getOutputFileFromUser()

            if (!inputFile.exists()) return println("Can't read input file!")

            println("Message to hide:")
            val message: String = readln()
            println("Password:")
            val password: String = readln()

            val encryptedMessage = encryptor.encrypt(message, password)
            val image = imageUtil.readImage(inputFile)
            val encryptedMessageBitsWithTerminator = (encryptedMessage + byteArrayOf(0, 0, 3)).toBitList()

            if (!imageUtil.canHideMessage(image, encryptedMessageBitsWithTerminator)) {
                return println("The input image is not large enough to hold this message.")
            }

            imageUtil.hideMessage(image, encryptedMessageBitsWithTerminator)
            imageUtil.saveImage(image, outputFile)
            println("Message saved in $outputFile image.")
        }
    }

    class ShowCommand : Command {
        private val console: Console = Console()
        private val imageUtil: ImageUtil = ImageUtil()
        private val encryptor: MessageEncryptor = MessageEncryptor()

        override fun execute() {
            val inputFile = console.getInputFileFromUser()
            if (!inputFile.exists()) return println("Can't read input file!")
            println("Password:")
            val password = readln()

            val image = imageUtil.readImage(inputFile)
            val encryptedMessage = imageUtil.extractMessage(image)
            val message = encryptor.decrypt(encryptedMessage, password)
            println("Message:\n$message")
        }
    }

    class ExitCommand : Command {
        override fun execute() {
            println("Bye!")
            exitProcess(0)
        }
    }
}
