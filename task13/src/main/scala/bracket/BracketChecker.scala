package bracket

import com.typesafe.scalalogging.LazyLogging

class BracketChecker extends LazyLogging {
  def check(s: String): Boolean = {
    logger.debug(s"Проверка строки: '$s'")
    val result = BracketUtils.isBalanced(s)
    if (result)
      logger.info(s"Строка сбалансирована: '$s'")
    else
      logger.warn(s"Строка не сбалансирована: '$s'")
    result
  }
}