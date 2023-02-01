import java.util.UUID
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef.jdbcFeeder
object Requests {
  val postAuto =exec(http("postAuto")
    .post("/Account/v1/Authorized")
    .check(status.is(200))
  )
  val getUserId =exec(session=>session.set("UUID",UUID.randomUUID().toString))
    .exec(http("getUserId")
    .get("/Account/v1/User/${UUID}")
    .check(status.is(200))
  )
  val postUser =exec(session=>session.set("userName",UUID.randomUUID().toString)
    .set("password", UUID.randomUUID().toString))
    .exec(
      http("postUser")
        .post("/Account/v1/User")
        .body(ElFileBody("body.json")).asJson
        .check(status.is(200))
    )
  val deleteBook = exec(session => session.set("isbn", UUID.randomUUID().toString)
    .set("userId", UUID.randomUUID().toString))
    .exec(
      http("deleteBook")
        .delete("/BookStore/v1/Book")
        .body(ElFileBody("bookBody.json")).asJson
        .check(status.is(200))
    )
}