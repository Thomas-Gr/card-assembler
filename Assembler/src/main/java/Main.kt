import ImageDataExtractor.computeAllImageData
import java.io.File

private const val INPUT_FOLDER = "xxx"
private const val OUTPUT_CARD = "xxx"

fun main() {
  val bindings = computeAllImageData(INPUT_FOLDER)

  val assembler = Assembler(bindings)

  File(INPUT_FOLDER)
      .walk()
      .filter { a -> !a.isDirectory }
      .toList()
      .parallelStream()
      .forEach {
        try {
          assembler.listFilesForFolder(
              INPUT_FOLDER + it.name,
              OUTPUT_CARD + it.name)
        } catch (e: Exception) {
          println(it)
          e.printStackTrace()
        }
      }
}