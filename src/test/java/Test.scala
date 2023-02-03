import DeleteRequests._
import GetRequests._
import PostRequest._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.language.postfixOps
import scala.concurrent.duration._

class Test extends Simulation{
  before {
    println("Simulation is about to start!")
  }

  after {
    println("Simulation is finished!")
  }
  val httpConf=http.baseUrl("https://demoqa.com/swagger/#/")
  val snc=scenario("Book Store") randomSwitch(
    (25,postAuto),
    (25,getUserId),
    (25,postUser),
    (25,deleteBook)
  )

  setUp(
    snc.inject(
      nothingFor(4),
      atOnceUsers(10),
      rampUsers(10).during(5 seconds),
      constantUsersPerSec(20).during(15 minutes),
      constantUsersPerSec(20).during(3 minutes).randomized,
      rampUsersPerSec(10).to(20).during(5 minutes ),
      rampUsersPerSec(10).to(20).during(5 minutes).randomized,
      stressPeakUsers(1000).during(5 minutes )
    ).protocols(httpConf)
  ).assertions(global.responseTime.max.lt(50),
    global.successfulRequests.percent.gt(95))
}
