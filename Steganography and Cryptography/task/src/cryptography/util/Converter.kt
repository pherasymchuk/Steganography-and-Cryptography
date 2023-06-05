package cryptography.util

class Converter {
    fun listOfBitsToCharString(bitList: List<Int>): String =
        bitList.joinToString("").chunked(8).map { it.toInt(2).toChar() }.joinToString("")

}
