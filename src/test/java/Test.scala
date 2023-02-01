import Requests._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps
import scala.concurrent.duration._

class Test extends Simulation{
  val httpConf=http.baseUrl("https://demoqa.com/swagger/#/")
  val snc=scenario("Book Store") randomSwitch(
    (25,postAuto),
    (25,getUserId),
    (25,postUser),
    (25,deleteBook)
  )

  setUp(snc.inject(constantUsersPerSec(100) during(1 minutes)).protocols(httpConf))
}
