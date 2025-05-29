import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio06 {

    public static void main(String[] args) {
        int createdId = 11;
        System.out.println("--- Buscando a entidade recém-criada com ID: " + createdId + " ---");

        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/" + createdId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            System.out.println("Status Code: " + statusCode);

            if (statusCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder responseBody = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }
                reader.close();
                System.out.println("Response Body (Entidade encontrada):\n" + responseBody.toString());
            } else {
                System.out.println("Não foi possível encontrar a entidade com ID " + createdId);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}