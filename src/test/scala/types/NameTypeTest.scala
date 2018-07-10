package types

import org.scalatest.FunSuite

class NameTypeTest extends FunSuite {

  test("testExtractFrom") {
    val testString = "Mr. A. Bramms, the coolest dude around"
    val result = NameType.extractFrom(testString)
    assertResult(Some("Mr. A. Bramms"))(result)
  }

  test("testClean") {
    //TODO Upon implementation of clean
  }

  test("testRegex") {
    val expected = """((?:\b[a-zA-Z]{2,6}\b\.?)?[ ]*(?:\b[a-zA-Z]+\b)??[ ]*(?:\b[a-zA-Z]\b\.?)?[ ]*(?:\b[a-zA-Z]+\b)??[ ]*(?:\b[a-zA-Z]\b\.?)?[ ]*(?:\b[a-zA-Z]{2,6}\b\.?)?)"""
    val actual = NameType.regex.pattern.toString
    assert(actual == expected)
  }

}
