import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.nio.charset.StandardCharsets
import java.nio.file.Files

class WordFrequencyCalculatorTest extends AnyFlatSpec with Matchers {
  
  val calculator = new WordFrequencyCalculator

  "WordFrequencyCalculator" should "calculate word frequencies from lines, respecting case" in {
    val lines = Iterator(
      "Hello world! This is a test.",
      "This test is only a TEST.",
      "Scala is great, and scala is powerful."
    )
    val result = calculator.calculateFromLines(lines)

    result shouldEqual Map(
      "Hello" -> 1, "world" -> 1, "This" -> 2, "is" -> 3, "a" -> 1, "test" -> 1,
      "only" -> 1, "TEST" -> 1, "Scala" -> 1, "great" -> 1, "and" -> 1,
      "scala" -> 1, "powerful" -> 1
    )
    result.size shouldBe 13
  }

  it should "treat case-sensitive words as distinct" in {
    val lines = Iterator("Scala scala SCALA sCaLa")
    val result = calculator.calculateFromLines(lines)
    result shouldEqual Map("Scala" -> 1, "scala" -> 1, "SCALA" -> 1, "sCaLa" -> 1)
    result.size shouldBe 4
  }

  it should "ignore extra punctuation and empty lines" in {
    val lines = Iterator(
      "",
      "!!! ??? ",
      "Scala. Scala, Scala; 'Scala'?"
    )
    val result = calculator.calculateFromLines(lines)
    result shouldEqual Map("Scala" -> 4)
    result.size shouldBe 1
  }

  it should "calculate word frequencies from a file, respecting case" in {
    val tmpFile = Files.createTempFile("testwords", ".txt")
    val content =
      """Hello world! This is a test.
        |This test is only a TEST.
        |Scala is great, and scala is powerful.""".stripMargin
    Files.write(tmpFile, content.getBytes(StandardCharsets.UTF_8))

    val result = calculator.calculateFromFile(tmpFile.toString)

    result shouldEqual Map(
      "Hello" -> 1, "world" -> 1, "This" -> 2, "is" -> 3, "a" -> 1, "test" -> 1,
      "only" -> 1, "TEST" -> 1, "Scala" -> 1, "great" -> 1, "and" -> 1,
      "scala" -> 1, "powerful" -> 1
    )
    result.size shouldBe 13

    Files.deleteIfExists(tmpFile)
  }
}