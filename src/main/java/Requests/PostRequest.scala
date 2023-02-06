import java.util.UUID
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object PostRequest {
  val postAuto = exec(http("postAuto")
    .post("/Account/v1/Authorized")
    .check(status.is(200))
  )
  val postUser = exec(session => session.set("userName", UUID.randomUUID().toString)
    .set("password", UUID.randomUUID().toString))
    .exec(
      http("postUser")
        .post("/Account/v1/User")
        .body(ElFileBody("body.json")).asJson
        .check(status.is(200))
    )
  private val userName = Iterator.continually {
    Map("userName" -> s"${Random.alphanumeric.take(20).mkString}@foo.com")
  }
  private val password = Iterator.continually {
    Map("password" -> s"${Random.alphanumeric.take(20).mkString}@foo.com")
  }
  val postUser2 = feed(userName).feed(password)
    .exec(
      http("postUser")
        .post("/Account/v1/User")
        .body(ElFileBody("body.json")).asJson
        .check(status.is(201))
    )
}
