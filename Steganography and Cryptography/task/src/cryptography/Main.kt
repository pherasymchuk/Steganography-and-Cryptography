package cryptography

import cryptography.command.Command
import cryptography.command.Command.*

class Runner(private val commandMap: Map<String, Command>) {
    fun run() {
        while (true) {
            println("Task (hide, show, exit):")
            val userInput = readln()
            val command = commandMap[userInput]
            if (command == null) {
                println("Invalid command!")
                continue
            }
            command.execute()
        }
    }
}

fun main() {
    val commandMap = mapOf(
        "hide" to HideCommand(),
        "show" to ShowCommand(),
        "exit" to ExitCommand()
    )
    Runner(commandMap).run()
}
