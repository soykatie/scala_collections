package bracket

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BracketCheckerTest extends AnyFlatSpec with Matchers {

  val checker = new BracketChecker

  "BracketChecker" should "approve balanced brackets" in {
    checker.check("([]){}") shouldBe true
    checker.check("((()))") shouldBe true
    checker.check("([{}])") shouldBe true
    checker.check("") shouldBe true
    checker.check("((((()))))") shouldBe true
  }

  it should "decline unbalanced brackets" in {
    checker.check("([)]") shouldBe false
    checker.check("([{})]") shouldBe false
    checker.check("([)]{[()]}") shouldBe false
    checker.check("(") shouldBe false
    checker.check(")") shouldBe false
    checker.check("(()") shouldBe false
    checker.check("())") shouldBe false
  }

  it should "ignore non-bracket characters" in {
    checker.check("a(b)c") shouldBe true
    checker.check("f{[()]}g") shouldBe true
    checker.check("a(b]c") shouldBe false
  }
}