import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio10 {

    public static void main(String[] args) {
        int idToDelete = 2;
        try {
            System.out.println("--- Tentando deletar entidade protegida com ID: " + idToDelete + " ---");
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/" + idToDelete);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int statusCode = connection.getResponseCode();
            System.out.println("Status Code: " + statusCode);

            if (statusCode == 403 || statusCode == 405) {
                System.out.println("Ação não permitida, como esperado.");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    errorResponse.append(line);
                }
                reader.close();
                System.out.println("Corpo da Resposta de Erro:\n" + errorResponse.toString());
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}