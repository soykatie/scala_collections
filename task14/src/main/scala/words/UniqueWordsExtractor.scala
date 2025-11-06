package words

import com.typesafe.scalalogging.LazyLogging

class UniqueWordsExtractor extends LazyLogging {
  def extractFromLines(lines: Iterator [String]): Set[String] = {
    logger.debug("Начато извлечение уникальных слов из строк.")
    val result = UniqueWordsUtils.extractUniqueWordsFromLines(lines)
    logger.info(s"Найдено уникальных слов: ${result.size}")
    result
  }

  def extractFromFile(filePath: String): Set[String] = {
    logger.debug(s"Извлекаем уникальные слова из файла: $filePath")
    val result = UniqueWordsUtils.extractUniqueWordsFromFile(filePath)
    logger.info(s"В файле '$filePath' найдено уникальных слов: ${result.size}")
    result
  }
}