package cryptography

import cryptography.command.Command
import cryptography.io.Reader
import java.io.File

fun main() {
    val reader: Reader = Reader.ConsoleReader() // selectReader()
    while (true) {
        println("Task (hide, show, exit):")
        val command = when (reader.readln()) {
            "hide" -> Command.HideCommand(reader)
            "show" -> Command.ShowCommand(reader)
            "exit" -> Command.ExitCommand()
            else -> {
                println("Invalid command!")
                continue
            }
        }
        command.execute()
    }
}

private fun selectReader(): Reader {
    println("Read from: (file, console (Default)):")
    return when (readln().trim().lowercase()) {
        "file" -> {
            println("Enter file name (1.input.txt 2.show.txt):")
            val file = when (readln()) {
                "1" -> File("input.txt")
                "2" -> File("show.txt")
                else -> throw IllegalArgumentException()
            }
            Reader.FileReader(file)
        }

        else -> Reader.ConsoleReader()
    }
}
