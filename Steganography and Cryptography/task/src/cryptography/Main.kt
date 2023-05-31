package cryptography

import cryptography.command.ExitCommand
import cryptography.command.HideCommand

fun main() {
    while (true) {
        println("Task (hide, show, exit):")
        val command = when (readln().lowercase()) {
            "hide" -> HideCommand()
            "exit" -> ExitCommand()
            else -> {
                println("Invalid command!")
                continue
            }
        }
        command.execute()
    }
}
