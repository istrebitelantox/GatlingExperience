import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class Mockib {
    @Rule
    public WireMockRule service1 = new WireMockRule(8081);

    @Rule
    public WireMockRule service2 = new WireMockRule(8082);

    @Test
    public void bothServicesDoStuff() throws JsonProcessingException {
        service1.stubFor(get(urlEqualTo("https://demoqa.com/swagger/")));
        Response response = RestAssured.given().when().body("body.json").post("/Account/v1/Authorized");
        System.out.println(("Response:"+response.getBody().asString()));
//        service2.stubFor(post(urlEqualTo("/blap")));

    }
}
