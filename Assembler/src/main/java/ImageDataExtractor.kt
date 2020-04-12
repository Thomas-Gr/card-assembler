import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object ImageDataExtractor {
  fun computeAllImageData(path: String): Map<String, Color> {
    return File(path)
        .walk()
        .filter { a -> !a.isDirectory }
        .map { it.absolutePath to extractAverageColor(it.absolutePath) }
        .toMap()
  }

  private fun extractAverageColor(path: String): Color {
    val image = ImageIO.read(File(path))
    return getAverageColor(image, 0, 0, image.width, image.height)
  }

  fun getAverageColor(myPicture: BufferedImage, x: Int, y: Int, w: Int, h: Int): Color {
    var blueTotal: Long = 0
    var greenTotal: Long = 0
    var redTotal: Long = 0

    for (i in x until x + w) {
      for (j in y until y + h) {
        val color = Color(myPicture.getRGB(i, j))
        blueTotal += color.blue.toLong()
        greenTotal += color.green.toLong()
        redTotal += color.red.toLong()
      }
    }

    blueTotal /= (w * h)
    greenTotal /= (w * h)
    redTotal /= (w * h)

    return Color(
        redTotal.toFloat() / 255,
        greenTotal.toFloat() / 255,
        blueTotal.toFloat() / 255)
  }
}