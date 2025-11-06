package words

import scala.collection.mutable.HashSet
import scala.io.Source


object UniqueWordsUtils {
  def extractUniqueWordsFromLines(lines: Iterator[String]): Set[String] = {
    val words = HashSet.empty[String]
    for (line <- lines) {
      line
        .split("\\W+")
        .map(_.toLowerCase)
        .filter(_.nonEmpty)
        .foreach(words += _)
    }
    words.toSet
  }

  def extractUniqueWordsFromFile(filePath: String): Set[String] = {
    val source = Source.fromFile(filePath)
    try {
      extractUniqueWordsFromLines(source.getLines())
    } finally {
      source.close()
    }
  }
}