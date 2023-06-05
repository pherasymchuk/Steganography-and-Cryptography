package cryptography.io

import java.io.File

interface Reader {
    fun readln(): String

    class ConsoleReader : Reader {
        override fun readln(): String = kotlin.io.readln()
    }

    class FileReader(file: File = File("input.txt")) : Reader {
        val iterator = file.readLines().listIterator()
        override fun readln(): String = iterator.next()
    }
}
