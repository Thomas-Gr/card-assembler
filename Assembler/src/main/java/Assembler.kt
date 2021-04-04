import ImageDataExtractor.getAverageColor
import org.imgscalr.Scalr
import java.awt.Color
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max

private const val MAXIMUM_USAGE = 20

private const val W = 3
private const val H = 2

private const val X_TARGET = 90
private const val Y_TARGET = 60

class Assembler(private val bindings: Map<String, Color>) {
  fun listFilesForFolder(targetFile: String, outputFile: String) {
    val targetPicture = ImageIO.read(File(targetFile))
    val resultImage = BufferedImage(
        floor(targetPicture.width.toDouble() / W).toInt() * X_TARGET,
        floor(targetPicture.height.toDouble() / H).toInt() * Y_TARGET,
        BufferedImage.TYPE_INT_RGB)
    val g2d = resultImage.createGraphics()
    val usage = mutableMapOf<String, Int>()

    generatePointListInRandomOrder(
        0, targetPicture.width, W,
        0, targetPicture.height, H)
        .forEach {
          (x, y) ->
            g2d.drawImage(
              rescaleAndCrop(getClosestPicture(usage, getAverageColor(targetPicture, x, y, W, H))),
              x * X_TARGET / W,
              y * Y_TARGET / H
            ) { _, _, _, _, _, _ -> false }
        }

    ImageIO.write(resultImage, "jpg", File(outputFile))
  }

  private fun rescaleAndCrop(closestPicture: String): Image {
    val inputImage = ImageIO.read(File(closestPicture))

    if (inputImage.width == X_TARGET && inputImage.height == Y_TARGET) {
      return inputImage
    }

    val scale =  max(
        X_TARGET.toDouble() / inputImage.width.toDouble(),
        Y_TARGET.toDouble() / inputImage.height.toDouble())
    val scaledImage = Scalr.resize(
        inputImage,
        Scalr.Method.ULTRA_QUALITY,
        ceil(inputImage.width * scale).toInt(),
        ceil(inputImage.height * scale).toInt())

    val newX = if (scaledImage.width > X_TARGET) (scaledImage.width - X_TARGET) / 2 else 0
    val newY = if (scaledImage.height > Y_TARGET) (scaledImage.height - Y_TARGET) / 2 else 0

    return Scalr.crop(scaledImage, newX, newY, X_TARGET, Y_TARGET)
  }

  private fun getClosestPicture(usage: MutableMap<String, Int>, averageColor: Color): String {
    var closestCard = ""
    var shortestDistance = Double.MAX_VALUE

    for ((key, imageColor) in bindings) {
      if (usage.getOrDefault(key,0) < MAXIMUM_USAGE) {
        val currentDistance = colorDistance(averageColor, imageColor)

        if (currentDistance < shortestDistance) {
          shortestDistance = currentDistance.toDouble()
          closestCard = key
        }
      }
    }

    if (closestCard.isEmpty()) {
      throw IllegalStateException("Not enough different pictures!")
    }

    usage.compute(closestCard) { _, oldValue -> if (oldValue == null) 1 else oldValue + 1 }

    return closestCard
  }

  private fun colorDistance(color1: Color, color2: Color): Int {
    return (abs(color2.red - color1.red)
        + abs(color2.green - color1.green)
        + abs(color2.blue - color1.blue))
  }

  private fun generatePointListInRandomOrder(
      startX: Int, endX: Int, incrementX: Int,
      startY: Int, endY: Int, incrementY: Int): List<Pair<Int, Int>> {
    val temporaryResult = mutableListOf<Pair<Int, Int>>()
    val randomlyOrderedResult = mutableListOf<Pair<Int, Int>>()

    var i = startX
    while (i <= endX - incrementX) {
      var j = startY
      while (j <= endY - incrementY) {
        temporaryResult.add(Pair(i, j))
        j += incrementY
      }
      i += incrementX
    }

    while (temporaryResult.isNotEmpty()) {
      randomlyOrderedResult.add(getNextRandomValue(temporaryResult))
    }

    return randomlyOrderedResult
  }

  private fun <X> getNextRandomValue(input: MutableList<X>): X {
    val n = input.size
    val index = (Math.random() * n).toInt()

    val num = input[index]

    input[index] = input[n - 1]
    input.removeAt(n - 1)

    return num
  }

}
