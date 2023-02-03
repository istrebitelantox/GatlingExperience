import java.util.UUID
import io.gatling.core.Predef._
import io.gatling.http.Predef._
object GetRequests {
  val getUserId = exec(session => session.set("UUID", UUID.randomUUID().toString))
    .exec(http("getUserId")
      .get("/Account/v1/User/${UUID}")
      .check(status.is(200))
    )
}
