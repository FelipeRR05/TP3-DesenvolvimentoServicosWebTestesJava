import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio07 {

    public static void main(String[] args) {
        try {
            System.out.println("--- Tentando atualizar entidade 10 com POST ---");
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/10");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonPayload = "{\"name\": \"atualizado\"}";

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(jsonPayload);
                outputStream.flush();
            }

            int statusCode = connection.getResponseCode();
            System.out.println("Status Code da atualização: " + statusCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();
            System.out.println("Corpo da resposta da atualização:\n" + responseBody.toString());
            connection.disconnect();

            System.out.println("\n--- Verificando a entidade 10 com GET ---");
            verifyEntity(10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void verifyEntity(int id) throws Exception {
        URL verifyUrl = new URL("https://apichallenges.eviltester.com/sim/entities/" + id);
        HttpURLConnection verifyConnection = (HttpURLConnection) verifyUrl.openConnection();
        verifyConnection.setRequestMethod("GET");

        int verifyStatusCode = verifyConnection.getResponseCode();
        System.out.println("Status Code da verificação: " + verifyStatusCode);

        BufferedReader verifyReader = new BufferedReader(new InputStreamReader(verifyConnection.getInputStream()));
        StringBuilder verifyResponseBody = new StringBuilder();
        String verifyLine;
        while ((verifyLine = verifyReader.readLine()) != null) {
            verifyResponseBody.append(verifyLine);
        }
        verifyReader.close();
        System.out.println("Corpo da resposta da verificação:\n" + verifyResponseBody.toString());
        verifyConnection.disconnect();
    }
}