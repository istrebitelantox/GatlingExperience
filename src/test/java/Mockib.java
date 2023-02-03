import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class Mockib {
    private WireMockServer wireMockServer;
    @Before
    public void setUp(){
        wireMockServer=new WireMockServer(options().port(8080));
        wireMockServer.start();
    }
    @Test
    public void test(){

    }
    @After
    public void tearDown(){
        wireMockServer.stop();
    }
    @Rule
    public WireMockRule wm=new WireMockRule(wireMockConfig().extensions(transformer));
}
