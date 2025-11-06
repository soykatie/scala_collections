import scala.collection.mutable.{HashMap}
import scala.io.Source


object UniqueWordsUtils {
  def calculateWordFrequenciesFromLines(lines: Iterator[String]): Map[String, Int] = {
    val wordFrequencies = HashMap.empty[String, Int]

    for (line <- lines) {
      line
        .split("\\W+")
        .filter(_.nonEmpty)
        .foreach { word =>
          wordFrequencies(word) = wordFrequencies.getOrElse(word, 0) + 1
        }
    }
    wordFrequencies.toMap
  }

  def calculateWordFrequenciesFromFile(filePath: String): Map[String, Int] = {
    val source = Source.fromFile(filePath)
    try {
      calculateWordFrequenciesFromLines(source.getLines())
    } finally {
      source.close()
    }
  }
}