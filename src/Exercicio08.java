import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio08 {

    public static void main(String[] args) {
        try {
            System.out.println("--- Atualizando entidade 10 com PUT ---");
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/10");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonPayload = "{\"name\": \"atualizado_com_put\"}";

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(jsonPayload);
                outputStream.flush();
            }

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