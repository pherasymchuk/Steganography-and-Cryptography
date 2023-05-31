import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.stage.StageTest
import org.hyperskill.hstest.testcase.CheckResult
import org.hyperskill.hstest.testing.TestedProgram
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.security.MessageDigest
import javax.imageio.ImageIO

class SteganographyTest: StageTest<Any>() {
    @DynamicTest
    fun imageCreateTest(): CheckResult {
        val main = TestedProgram()
        var outputString = main.start().toLowerCase().trim()
        if (!outputString.contains("task (hide, show, exit):")) {
            return CheckResult(false, "Prompt \"Task (hide, show, exit):\" is missing.")
        }

        outputString = main.execute("hide").toLowerCase().trim()
        if (!outputString.contains("input image file:")) {
            return CheckResult(false, "Wrong output after the \"hide\" command.")
        }

        try {
            val infile = "test/testimage.png"
            val inputFile = File(infile)
            if (!inputFile.exists()) {
                return CheckResult(false, "Input test image file doesn't exist. Try reloading the project.")
            }
            val inputImage: BufferedImage = ImageIO.read(inputFile)
            if (imageHash(inputImage) != "cc5bc38fad61e51b44ec7731f49ae2e290c06f55") {
                return CheckResult(false, "Input test image file is corrupted. Try reloading the project.")
            }

            outputString = main.execute(infile).toLowerCase().trim()
            if (!outputString.contains("output image file:")) {
                return CheckResult(false, "Wrong output after inserting the input filename.")
            }

            val outfile = "test/out.png"
            outputString = main.execute(outfile).toLowerCase().trim()
            if (!( outputString.contains("input image") && outputString.contains("test/testimage.png") &&
                        outputString.contains("output image") && outputString.contains("test/out.png") &&
                        outputString.contains("is saved") && outputString.contains("task (hide, show, exit):") ) )
            {
                return CheckResult(false, "Wrong output after inserting the output filename.")
            }
            val outputFile = File(outfile)
            if (!outputFile.exists()) {
                return CheckResult(false, "Image file $outfile hasn't been created.")
            }
            val outputImage: BufferedImage = ImageIO.read(outputFile)
            if (imageHash(outputImage) != "8cd439311726d955682667aef835d47e01a05eb8") {
                return CheckResult(false, "Wrong output file. Wrong output image hash.")
            }
        } catch (e: Exception) {
            return CheckResult(false, e.message)
        }


        outputString = main.execute("exit").toLowerCase().trim()
        if (!outputString.contains("bye!")) {
            return CheckResult(false, "Wrong output after the \"exit\" command.")
        }

        if (!main.isFinished) {
            return CheckResult(false, "Program has not terminated after the \"exit\" command.")
        }

        return CheckResult(true, "")
    }

    @DynamicTest
    fun nonExistFileTest(): CheckResult {
        val main = TestedProgram()
        var outputString = main.start().toLowerCase().trim()
        if (!outputString.contains("task (hide, show, exit):")) {
            return CheckResult(false, "Prompt \"Task (hide, show, exit):\" is missing.")
        }

        outputString = main.execute("hide").toLowerCase().trim()
        if (!outputString.contains("input image file:")) {
            return CheckResult(false, "Wrong output after the \"hide\" command.")
        }

        outputString = main.execute("test/notexist.png").toLowerCase().trim()
        if (outputString.contains("output image file:")) {
            outputString = main.execute("test/out.png").toLowerCase().trim()
        }
        if ( !( outputString.contains("can't read input file")
                    && outputString.contains("task (hide, show, exit):") ) ) {
            return CheckResult(false, "Wrong output after non existing input file.")
        }

        outputString = main.execute("exit").toLowerCase().trim()
        if (!outputString.contains("bye!")) {
            return CheckResult(false, "Wrong output after the \"exit\" command.")
        }

        if (!main.isFinished) {
            return CheckResult(false, "Program has not terminated after the \"exit\" command.")
        }

        return CheckResult(true, "")
    }

    private fun imageHash(inputImage: BufferedImage) : String {
        val imageByteArray = ByteArray(3 * inputImage.width * inputImage.height)
        var index = 0
        for (y in 0 until inputImage.height) {
            for (x in 0 until inputImage.width) {
                val color = Color(inputImage.getRGB(x, y))
                imageByteArray[index] = color.red.toByte()
                index++
                imageByteArray[index] = color.green.toByte()
                index++
                imageByteArray[index] = color.blue.toByte()
                index++
            }
        }
        val md = MessageDigest.getInstance("SHA-1")
        md.update(imageByteArray)
        return md.digest().joinToString("") { "%02x".format(it) }
    }

}

