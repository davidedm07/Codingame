package scala

object MimeType extends App {
  val n = readInt // Number of elements which make up the association table.
  val q = readInt // Number Q of file names to be analyzed.
  val extensionsMap = scala.collection.mutable.HashMap.empty[String, String]
  for (i <- 0 until n) {
    // ext: file extension
    // mt: MIME type.
    val Array(ext, mt) = readLine split " "
    extensionsMap(ext.toLowerCase) = mt
  }

  for (i <- 0 until q) {
    val fname = readLine // One file name per line.
    val lastDot = fname.lastIndexOf(".")
    val ext = if (lastDot == fname.length || lastDot == -1) "UNKNOWN" else fname.substring(lastDot + 1, fname.length)
    println(extensionsMap.getOrElse(ext.toLowerCase, "UNKNOWN"))
  }

  // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
}
