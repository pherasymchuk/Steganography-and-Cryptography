package cryptography.util

import java.awt.Color
import java.awt.image.BufferedImage

fun ByteArray.toBitList(): MutableList<Int> {
    val bitList = mutableListOf<Int>()
    map { it.toString(2).padStart(8, '0') }.forEach { byte ->
        byte.forEach { bit ->
            bitList.add(bit.digitToInt())
        }
    }
    return bitList
}

class Steganography {
    private val bitOperator = BitOperator()
    fun hideMessage(image: BufferedImage, messageArray: ByteArray) {
        val bitListIterator = messageArray.toBitList().listIterator()

        Row@ for (y in 0 until image.height) {
            Column@ for (x in 0 until image.width) {
                if (bitListIterator.hasNext()) {
                    val color = Color(image.getRGB(x, y))
                    val modifiedBlue = bitOperator.setLeastSignificantBit(color.blue, bitListIterator.next())
                    val modifiedColor = Color(color.red, color.green, modifiedBlue)
                    image.setRGB(x, y, modifiedColor.rgb)
                } else break@Row
            }
        }
    }

    fun extractMessage(image: BufferedImage): String {
        val terminatorBits = listOf(0, 0, 3).joinToString("") { it.toByte().toString(2).padStart(8, '0') }
        val bitList = mutableListOf<Int>()

        Row@ for (y in 0 until image.height) {
            Column@ for (x in 0 until image.width) {
                if (bitList.size >= 24 && bitList.takeLast(24).joinToString("") == terminatorBits) {
                    repeat(24) { bitList.removeLast() }
                    break@Row
                }

                val blueColorValue = Color(image.getRGB(x, y)).blue
                val leastSignificantBit = bitOperator.getLeastSignificantBit(blueColorValue)
                bitList.add(leastSignificantBit)
            }
        }
        return bitOperator.listOfBitsToCharString(bitList)
    }

    fun canHideMessage(image: BufferedImage, messageBytes: ByteArray): Boolean =
        messageBytes.toBitList().size <= image.width * image.height
}
