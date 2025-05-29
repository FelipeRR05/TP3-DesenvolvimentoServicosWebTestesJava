import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio04 {

    public static void main(String[] args) {
        try {
            String baseUrl = "https://apichallenges.eviltester.com/sim/entities";
            String queryParams = "?categoria=teste&limite=5";
            URL url = new URL(baseUrl + queryParams);

            System.out.println("URL Final Montada: " + url.toString());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            System.out.println("Status Code: " + statusCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();
            System.out.println("Response Body:\n" + responseBody.toString());


            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}