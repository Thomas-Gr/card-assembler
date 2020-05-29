import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import common.LOG_FILE
import common.MOSAIC_INPUT_FOLDER
import common.S3_BUCKET_NAME
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

fun main() {
  val s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_WEST_3).build();

  val filesDone = mutableSetOf<String>()
  File(LOG_FILE).forEachLine { filesDone.add(it) }

  File(MOSAIC_INPUT_FOLDER)
      .walk()
      .filter { it.isFile }
      .toList()
      .parallelStream()
      .forEach {
        try {
          val id = it.path.substringAfter(MOSAIC_INPUT_FOLDER)
          if (!filesDone.contains(id)) {
            uploadFile(s3, id)

            Files.write(
                Paths.get(LOG_FILE),
                "%s\n".format(id).toByteArray(),
                StandardOpenOption.APPEND)
          } else {
            println("Skipping  %s".format(id))
          }
        } catch (e: Throwable) {
          println(it.path)
        }
      }
}

private fun uploadFile(s3: AmazonS3, path: String) {
  val request = PutObjectRequest(
      S3_BUCKET_NAME,
      "images/mosaic/%s".format(path),
      File("%s%s".format(MOSAIC_INPUT_FOLDER, path)))

  val metadata = ObjectMetadata()
  metadata.contentType = "image/jpeg"
  request.metadata = metadata
  s3.putObject(request)
}