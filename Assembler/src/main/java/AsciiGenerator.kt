import ImageDataExtractor.getAverageGrayValue
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.round

private const val CHARS = " .:-=+*#%@"

class AsciiGenerator {
  fun listFilesForFolder(targetFile: String) {
    val targetPicture = ImageIO.read(File(targetFile))

    //printToConsole(targetPicture)

    println("<html><body style='background-color:black;line-height: 0;font-family: \"Courier New\", Courier, monospace;'>")
    for (i in 0 until targetPicture.height) {
      for (j in 0 until targetPicture.width) {
        val char = pickAsciiCharacter(getAverageGrayValue(targetPicture, j, i, 1, 1))

        val color = Color(targetPicture.getRGB(j, i))
        print("<span style='color:rgb(%s, %s, %s)'>%s<span>"
          .format(color.red, color.green, color.blue, char))
      }
      println("<br>")
    }

    println("</body></html>")
  }

  private fun printToConsole(targetPicture: BufferedImage) {
    for (i in 0 until targetPicture.height) {
      for (j in 0 until targetPicture.width) {
        val char = pickAsciiCharacter(getAverageGrayValue(targetPicture, j, i, 1, 1))

        print(char)
      }
      println()
    }
  }

  private fun pickAsciiCharacter(grayValue: Double) =
    CHARS[round(grayValue * (CHARS.length - 1)).toInt()]
}
