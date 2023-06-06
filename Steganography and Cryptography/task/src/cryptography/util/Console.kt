package cryptography.util

import java.io.File

class Console {
    fun getInputFileFromUser(): File {
        println("Input image file:")
        return File(readln())
    }
    fun getOutputFileFromUser(): File {
        println("Output image file:")
        return File(readln())
    }
}
