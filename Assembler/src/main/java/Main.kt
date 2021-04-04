@file:JvmName("OutputGenerator")

import ImageDataExtractor.computeAllImageData
import common.INPUT_FOLDER
import common.OUTPUT_CARD
import java.io.File

fun main() {
  val asciiGenerator = AsciiGenerator()

  File(INPUT_FOLDER)
    .walk()
    .filter { !it.isDirectory }
    .toList()
    .take(1)
    .forEach {
      try {
        asciiGenerator.listFilesForFolder(INPUT_FOLDER + it.name)
      } catch (e: Exception) {
        println(it)
        e.printStackTrace()
      }
    }

  //generateMosaic()
}

private fun generateMosaic() {
  val bindings = computeAllImageData(INPUT_FOLDER)

  val assembler = Assembler(bindings)

  File(INPUT_FOLDER)
    .walk()
    .filter { !it.isDirectory }
    .toList()
    .parallelStream()
    .forEach {
      try {
        assembler.listFilesForFolder(
          INPUT_FOLDER + it.name,
          OUTPUT_CARD + it.name
        )
      } catch (e: Exception) {
        println(it)
        e.printStackTrace()
      }
    }
}