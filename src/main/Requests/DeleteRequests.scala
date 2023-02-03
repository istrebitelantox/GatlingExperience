import java.util.UUID
import io.gatling.core.Predef._
import io.gatling.http.Predef._
object DeleteRequests {
  val deleteBook = exec(session => session.set("isbn", UUID.randomUUID().toString)
    .set("userId", UUID.randomUUID().toString))
    .exec(
      http("deleteBook")
        .delete("/BookStore/v1/Book")
        .body(ElFileBody("bookBody.json")).asJson
        .check(status.is(200))
    )
}
