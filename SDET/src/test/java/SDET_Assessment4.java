import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SDET_Assessment4 {
    @Test
    public void testAPIResponseTime() {
        try {
            // Get current time before making the API call
            long startTime = System.currentTimeMillis();

            // Make API call
            URL url = new URL("https://open.er-api.com/v6/latest/USD");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Check if the response code is successful (200)
            int responseCode = conn.getResponseCode();
            Assert.assertEquals(responseCode, 200, "API call failed with response code: " + responseCode);

            // Read and parse the response
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Validate the response
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Check status
            String status = jsonResponse.getString("status");
            Assert.assertEquals(status, "success", "API status is not success: " + status);

            // Get current time after receiving the response
            long endTime = System.currentTimeMillis();

            // Calculate the API response time in seconds
            long responseTimeSeconds = (endTime - startTime) / 1000;

            // Check if the response time is greater than or equal to 3 seconds
            Assert.assertTrue(responseTimeSeconds >= 3, "API response time is less than 3 seconds: " + responseTimeSeconds + " seconds");

            System.out.println("API response time: " + responseTimeSeconds + " seconds");
        } catch (IOException e) {
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
}

