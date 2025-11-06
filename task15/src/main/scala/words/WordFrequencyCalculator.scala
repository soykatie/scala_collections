import com.typesafe.scalalogging.LazyLogging

class WordFrequencyCalculator extends LazyLogging {
  def calculateFromLines(lines: Iterator [String]): Map[String, Int] = {
    logger.debug("Начато извлечение частот слов из строк.")
    val result = UniqueWordsUtils.calculateWordFrequenciesFromLines(lines)
    logger.info(s"Найдено различных слов (с учетом регистра): ${result.size}")
    result
  }

  def calculateFromFile(filePath: String): Map[String, Int] = {
    logger.debug(s"Извлекаем частоты слов из файла: $filePath")
    val result = UniqueWordsUtils.calculateWordFrequenciesFromFile(filePath)
    logger.info(s"В файле '$filePath' найдено различных слов: ${result.size}")
    result
  }
}