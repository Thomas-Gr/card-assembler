import ImageDataExtractor.computeAllImageData
import common.INPUT_FOLDER
import common.OUTPUT_CARD
import java.io.File

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