import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio11 {

    public static void main(String[] args) {
        try {
            System.out.println("--- Verificando métodos permitidos com OPTIONS ---");
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("OPTIONS");

            int statusCode = connection.getResponseCode();
            System.out.println("Status Code: " + statusCode);

            String allowedMethods = connection.getHeaderField("Allow");
            System.out.println("Métodos HTTP Permitidos (cabeçalho Allow): " + allowedMethods);

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}