package types

import org.scalatest.FunSuite

class PhoneTypeTest extends FunSuite {

  test("testExtractFrom") {
    val testString = "+1 (234) 567 8910 ext 92340 is my cell number"
    val result = PhoneType.extractFrom(testString)
    val expected = "(+1)(234)567-8910(ext:92340)"
    assertResult(Some(expected))(result)
  }

  test("testClean") {
    val testString = "+12345678910x1234"
    val result = PhoneType.clean(testString)
    val expected = "(+1)(234)567-8910(ext:1234)"
    assertResult(expected)(result)
  }

  test("testRegex") {
    val expected = """((?:\+{0,2}\d{1,3})?[-.()\/* ]*\d{3}[-.()\/* ]*\d{3}[-.()\/* ]*\d{4}[-.()\/* ]*(?:(?:x|ext)[:]?[ ]*\d+)?)"""
    val actual = PhoneType.regex.pattern.toString
    assert(actual == expected)
  }

}
