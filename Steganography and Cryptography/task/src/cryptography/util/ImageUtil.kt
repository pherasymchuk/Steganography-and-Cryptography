package cryptography.util

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun ByteArray.toBitList(): MutableList<Int> {
    val bitList = mutableListOf<Int>()
    map { it.toString(2).padStart(8, '0') }.forEach { byte ->
        byte.forEach { bit ->
            bitList.add(bit.digitToInt())
        }
    }
    return bitList
}

class ImageUtil {
    private val converter = Converter()
    fun readImage(file: File): BufferedImage = ImageIO.read(file)

    fun saveImage(image: BufferedImage, outputFile: File) {
        val format = "png"
        ImageIO.write(image, format, outputFile)
    }

    fun canHideMessage(image: BufferedImage, messageBits: MutableList<Int>): Boolean = messageBits.size <= image.height * image.width

    fun hideMessage(image: BufferedImage, messageBits: List<Int>) {
        val bitsIterator = messageBits.listIterator()

        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                if (!bitsIterator.hasNext()) return
                val color = Color(image.getRGB(x, y))
                val modifiedBlue = setLeastSignificantBit(color.blue, bitsIterator.next())
                val modifiedColor = Color(color.red, color.green, modifiedBlue)
                image.setRGB(x, y, modifiedColor.rgb)
            }
        }
    }

    fun extractMessage(image: BufferedImage): ByteArray {
        val terminator = byteArrayOf(0, 0, 3).toBitList()
        val bitList = mutableListOf<Int>()

        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                if (bitList.size >= 24 && bitList.takeLast(24) == terminator) {
                    repeat(24) { bitList.removeLast() }
                    return converter.listOfBitsToByteArray(bitList)
                }
                val blue = Color(image.getRGB(x, y)).blue
                val leastSignificantBit = getLeastSignificantBit(blue)
                bitList.add(leastSignificantBit)
            }
        }
        return converter.listOfBitsToByteArray(bitList)
    }

    private fun setLeastSignificantBit(colorToChange: Int, bit: Int): Int =
        if (bit == 1) colorToChange or 0b00000001 else colorToChange and 0b11111110

    private fun getLeastSignificantBit(value: Int): Int = value and 1
}
