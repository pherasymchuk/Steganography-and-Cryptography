package cryptography.util

import cryptography.io.Reader
import java.io.File

class Console(private val reader: Reader) {
    fun getInputFileFromUser(): File {
        println("Input image file:")
        return File(reader.readln())
    }
    fun getOutputFileFromUser(): File {
        println("Output image file:")
        return File(reader.readln())
    }
    fun getMessageWithTerminator(): ByteArray = reader.readln().toByteArray() + listOf(0, 0, 3)
}
