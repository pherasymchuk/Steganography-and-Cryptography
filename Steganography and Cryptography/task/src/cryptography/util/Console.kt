package cryptography.util

import java.io.File

object Console {
    fun getInputFileFromUser(): File {
        // val filePath = "${workingDir}${separator}Steganography and Cryptography${separator}task${separator}src${separator}cryptography${separator}image.jpg"
        println("Input image file:")
        return File(readln())
    }
    fun getOutputFileFromUser(): File {
        println("Output image file:")
        return File(readln())
    }
}
