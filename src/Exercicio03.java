import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio03 {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/13");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            System.out.println("Status Code: " + statusCode);

            if (statusCode == 404) {
                System.out.println("Mensagem: Entidade com o ID 13 não foi encontrada, como esperado.");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                StringBuilder errorResponse = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    errorResponse.append(line);
                }
                reader.close();
                System.out.println("Corpo do Erro:\n" + errorResponse.toString());
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}