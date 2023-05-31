package cryptography.command

import kotlin.system.exitProcess

class ExitCommand : Command {
    override fun execute() {
        println("Bye!")
        exitProcess(0)
    }
}
