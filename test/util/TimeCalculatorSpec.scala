package util

import org.specs2.mutable.Specification

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-03-21
 * Time: 21:57
 */

class TimeCalculatorSpec extends Specification {

  "TimeCalculator" should {

    "Test pace calculation" in {
      TimeCalculator.calculatePace("01:00:00", 10) must beEqualTo(6.0)
    }

  }

}
