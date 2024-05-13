import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SDET_Assessment3
{

    @Test
    public void testUSDPrice()
    {
        try {

            URL url = new URL("https://open.er-api.com/v6/latest/USD");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            Assert.assertEquals(responseCode, 200, "API call failed with response code: " + responseCode);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            String jsonResponse = response.toString();
            String status = getStatus(jsonResponse);
            Assert.assertEquals(status, "success", "API call failed with status: " + status);

            System.out.println("Status: " + status);
            String usdPrice = jsonResponse.split("\"USD\":")[1].split(",")[0];
            double price = Double.parseDouble(usdPrice);
            Assert.assertTrue(price > 0, "Invalid USD price: " + price);

            System.out.println("USD price: " + price);
        } catch (IOException e) {
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }

    private String getStatus(String jsonResponse)
    {
        if (jsonResponse.contains("\"status\":")) {
            int startIndex = jsonResponse.indexOf("\"status\":\"") + 10;
            int endIndex = jsonResponse.indexOf("\"", startIndex);
            return jsonResponse.substring(startIndex, endIndex);
        }
        return "";
    }
}
