package cryptography.io

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageIOHandler {
    fun readImage(file: File): BufferedImage = ImageIO.read(file)

    fun writeImage(image: BufferedImage, file: File) {
        ImageIO.write(image, "png", file)
    }
}
