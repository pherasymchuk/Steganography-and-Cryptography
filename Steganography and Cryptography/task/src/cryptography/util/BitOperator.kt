package cryptography.util

class BitOperator {
    fun setLeastSignificantBit(colorToChange: Int, charBit: Int): Int =
        if (charBit == 1) colorToChange or 0b00000001 else colorToChange and 0b11111110

    fun getLeastSignificantBit(value: Int): Int = value and 1

    fun listOfBitsToCharString(bitList: List<Int>): String =
        bitList.joinToString("").chunked(8).map { it.toInt(2).toChar() }.joinToString("")
}
