import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio09 {

    public static void main(String[] args) {
        int idToDelete = 9;
        try {
            System.out.println("--- Deletando entidade com ID: " + idToDelete + " ---");
            URL deleteUrl = new URL("https://apichallenges.eviltester.com/sim/entities/" + idToDelete);
            HttpURLConnection deleteConnection = (HttpURLConnection) deleteUrl.openConnection();
            deleteConnection.setRequestMethod("DELETE");

            int deleteStatusCode = deleteConnection.getResponseCode();
            System.out.println("Status Code do DELETE: " + deleteStatusCode);
            deleteConnection.disconnect();

            if (deleteStatusCode == 204 || deleteStatusCode == 200) {
                System.out.println("Entidade deletada com sucesso.");
            } else {
                System.out.println("Falha ao deletar a entidade.");
            }

            System.out.println("\n--- Verificando se a entidade " + idToDelete + " foi removida ---");
            URL getUrl = new URL("https://apichallenges.eviltester.com/sim/entities/" + idToDelete);
            HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
            getConnection.setRequestMethod("GET");

            int getStatusCode = getConnection.getResponseCode();
            System.out.println("Status Code do GET pós-delete: " + getStatusCode);

            if (getStatusCode == 404) {
                System.out.println("Recebido status 404, como esperado. A entidade não existe mais.");
            }
            getConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}