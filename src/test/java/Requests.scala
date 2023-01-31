import java.util.UUID
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef.jdbcFeeder
object Requests {
  val newsList =exec(http("NewsList")
    .post("/Account/v1/Authorized")
    .check(status.is(200)))
  val newsItem =exec(session=>session.set("UUID",UUID.randomUUID().toString)).exec(http("Auto")
    .get("/Account/v1/User/${UUID}")
    .check(status.is(200)))
}