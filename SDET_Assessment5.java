import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SDET_Assessment5 {
    @Test
    public void testAPIResponseTime() {
        try {

            long startTime = System.currentTimeMillis();

            // Make API call
            URL url = new URL("https://open.er-api.com/v6/latest/USD");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            Assert.assertEquals(responseCode, 200, "API call failed with response code: " + responseCode);

            // Read and parse the response
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            JSONObject jsonResponse = new JSONObject(response.toString());
            String status = jsonResponse.getString("status");
            Assert.assertEquals(status, "success", "API status is not success: " + status);
            long endTime = System.currentTimeMillis();
            long responseTimeSeconds = (endTime - startTime) / 1000;
            Assert.assertTrue(responseTimeSeconds >= 3, "API response time is less than 3 seconds: " + responseTimeSeconds + " seconds");
            System.out.println("API response time: " + responseTimeSeconds + " seconds");
        } catch (IOException e) {
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
}
