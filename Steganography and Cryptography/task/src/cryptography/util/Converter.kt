package cryptography.util

class Converter {
    fun listOfBitsToByteArray(bitList: List<Int>): ByteArray =
        bitList.joinToString("").chunked(8).map { it.toByte(2) }.toByteArray()

    fun byteArrayToCharString(byteArray: ByteArray): String {
        return byteArray.map { it.toInt().toChar() }.joinToString("")
    }
}
