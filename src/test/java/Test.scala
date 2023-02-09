import PostRequest._
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.{ok, urlEqualTo}
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps
class Test extends Simulation{
  val Port = 8080
  val Host = "localhost"
  val wireMock=new WireMock()
  val wireMockServer = new WireMockServer()
  before {
    println("Simulation is about to start!")
    wireMockServer.start()
    WireMock.configureFor(Host,Port)
    wireMockServer.stubFor(WireMock.post(urlEqualTo("/Account/v1/User")).willReturn(ok()))
  }

  after {
    println("Simulation is finished!")
    wireMockServer.stop()
  }
  val httpConf=http.baseUrl("http://localhost:8080/")
  val snc=scenario("Book Store").exec(postUser)

  /*randomSwitch(
    (25,postAuto),
    (25,getUserId),
    (25,postUser),
    (25,deleteBook)
  )*/

  setUp(
    snc.inject(
      nothingFor(4),
      atOnceUsers(10),
      rampUsers(10).during(5 seconds),
      constantUsersPerSec(20).during(15 seconds),
      constantUsersPerSec(20).during(3 seconds).randomized,
      rampUsersPerSec(10).to(20).during(5 seconds ),
      rampUsersPerSec(10).to(20).during(5 seconds).randomized,
      stressPeakUsers(1000).during(5 seconds )
    ).protocols(httpConf)
  )/*.assertions(global.responseTime.max.lt(50),
    global.successfulRequests.percent.gt(95))*/
}
