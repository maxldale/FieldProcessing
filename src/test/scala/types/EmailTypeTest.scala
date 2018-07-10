package types

import org.scalatest.FunSuite

class EmailTypeTest extends FunSuite {

  test("testExtractFrom") {
    val testString = "some words and an@email.com"
    val result = EmailType.extractFrom(testString)
    assertResult(Some("an@email.com"))(result)
  }

  test("testClean") {
    //TODO Upon implementation of clean
  }

  test("testRegex") {
    val expected = """(\b[0-9a-zA-Z](?:[0-9a-zA-Z-\+\._]*[0-9a-zA-Z])@[0-9a-zA-Z][0-9a-zA-Z-\.]+[0-9a-zA-Z]\.[a-zA-Z]{2,5}\b)"""
    val actual = EmailType.regex.pattern.toString
    assert(expected == actual)
  }

}
