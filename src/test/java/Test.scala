import Requests._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps
import scala.concurrent.duration._

class Test extends Simulation{
  val httpConf=http.baseUrl("https://demoqa.com/swagger/#/")
  val snc=scenario("CodeFest") randomSwitch(
    (50,newsList),
    (50,newsItem)
  )

  setUp(snc.inject(constantUsersPerSec(100) during(15 seconds)).protocols(httpConf))
}
