package bracket

object BracketUtils {
  private val pairs: Map[Char, Char] = Map(
    ')' -> '(',
    ']' -> '[',
    '}' -> '{'
  )

  def isBalanced(s: String): Boolean = {
    s.foldLeft(List.empty[Char]) { (stack, char) =>
      char match {
        case '(' | '[' | '{' => char :: stack
        case ')' | ']' | '}' =>
          stack match {
            case head :: tail if head == pairs(char) => tail
            case _ => return false
          }
        case _ => stack
      }
    }.isEmpty
  }
}