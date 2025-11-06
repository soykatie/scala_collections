package words

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.nio.file.{Files, Paths}
import java.nio.charset.StandardCharsets

class UniqueWordsExtractorSpec extends AnyFlatSpec with Matchers {

  val extractor = new UniqueWordsExtractor

  "UniqueWordsExtractor" should "extract unique words from lines" in {
    val lines = Iterator(
      "Hello world! This is a test.",
      "This test is only a TEST.",
      "Scala is great, and scala is powerful."
    )
    val result = extractor.extractFromLines(lines)
    result should contain allOf ("hello", "world", "this", "is", "a", "test", "only", "scala", "great", "and", "powerful")
    result.size shouldBe 11
  }

  it should "treat case-insensitive words as equal" in {
    val lines = Iterator("Scala scala SCALA sCaLa")
    val result = extractor.extractFromLines(lines)
    result shouldEqual Set("scala")
  }

  it should "ignore extra punctuation and empty lines" in {
    val lines = Iterator(
      "",
      "!!! ??? ",
      "Scala. Scala, Scala; 'Scala'?"
    )
    val result = extractor.extractFromLines(lines)
    result shouldEqual Set("scala")
  }

  it should "extract unique words from a file" in {
    val tmpFile = Files.createTempFile("testwords", ".txt")
    val content =
      """Hello world! This is a test.
        |This test is only a TEST.
        |Scala is great, and scala is powerful.""".stripMargin
    Files.write(tmpFile, content.getBytes(StandardCharsets.UTF_8))

    val result = extractor.extractFromFile(tmpFile.toString)
    result should contain allOf ("hello", "world", "this", "is", "a", "test", "only", "scala", "great", "and", "powerful")
    result.size shouldBe 11

    Files.deleteIfExists(tmpFile)
  }
}