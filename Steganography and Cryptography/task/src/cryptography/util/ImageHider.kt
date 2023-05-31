package cryptography.util

import java.awt.Color
import java.awt.image.BufferedImage

class ImageHider {
    private val bitOperator = BitOperator()
    fun hideImage(image: BufferedImage) {
        for (x in 0 until image.width) {
            for (y in 0 until image.height) {
                val color = Color(image.getRGB(x, y))
                val modifiedColors = bitOperator.run {
                    Color(
                        setLeastSignificantBitToOne(color.red),
                        setLeastSignificantBitToOne(color.green),
                        setLeastSignificantBitToOne(color.blue)
                    )
                }
                image.setRGB(x, y, modifiedColors.rgb)
            }
        }
    }
}
