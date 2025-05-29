import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio01 {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            System.out.println("Status Code: " + statusCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBody = new StringBuilder();

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