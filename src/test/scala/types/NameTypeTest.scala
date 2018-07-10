package types

import org.scalatest.FunSuite

class NameTypeTest extends FunSuite {

  test("testExtractFrom") {
    val testString = "Mr. A. Bramms, the coolest dude around"
    val result = NameType.extractFrom(testString)
    val expected = Some("Mr. A. Bramms")
    assertResult(expected)(result)
  }

  test("testClean") {
    //TODO Upon implementation of clean
  }

  test("testRegex") {
    val expected = """((?:\b[a-zA-Z]{2,6}\b\.?[ ]*)?(?:(?:\b[a-zA-Z]\b\.?|\b[a-zA-Z]+\b)[ ]*)?(?:(?:\b[a-zA-Z]\b\.?|\b[a-zA-Z]+\b)[ ]*)?(?:(?:\b[a-zA-Z]\b\.?|\b[a-zA-Z]+\b)[ ]*)?(?:\b[a-zA-Z]{2,6}\b\.?)?)"""
    val actual = NameType.regex.pattern.toString
    assert(actual == expected)
  }

  test("specificCaseTest") {
    val testString = """Paul Ripp
                       |rripp@centurytel.net
                       |PH: 715-2849866
                       |Fax: 715-284-8476
                       |Black River Falls, WI""".stripMargin
    val expected = Some("Paul Ripp")
    val actual = NameType.extractFrom(testString)
    assertResult(expected)(actual)
  }

  test("specificCaseTestTwo") {
    val testString = "Paul Burkholder, 717-682-8155"
    val expected = Some("Paul Burkholder")
    val actual = NameType.extractFrom(testString)
    assertResult(expected)(actual)
  }

}
