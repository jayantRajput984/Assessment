import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SDET_Assessment1 {

    @Test
    void Test_GetRequest() {
        Response response = RestAssured.get("https://open.er-api.com/v6/latest/USD");
        System.out.println("Status code :" + response.asString());
        System.out.println("Status code :" + response.getStatusCode());
        System.out.println("Status code :" + response.getTime());
        System.out.println("Status code :" + response.getHeaders());
        System.out.println("Status code :" + response.getBody().asString());
        response.getStatusCode();
        response.getBody();
        response.getHeaders();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }
}

