import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class Exercicio12 {

    private static final String BASE_URL = "https://apichallenges.eviltester.com/simpleapi";

    public static void main(String[] args) {
        try {
            System.out.println("--- 1. GET todos os itens ---");
            getItems();

            System.out.println("\n--- 2. Gerar ISBN aleatório ---");
            String isbn = getRandomIsbn();
            System.out.println("ISBN Gerado: " + isbn);

            System.out.println("\n--- 3. Criar item com POST ---");
            createItem(isbn, "Livro de Teste", "Aluno");

            System.out.println("\n--- 4. Atualizar item com PUT ---");
            updateItem(isbn, "Livro de Teste Atualizado", "Aluno Dedicado");

            System.out.println("\n--- 5. Remover item com DELETE ---");
            deleteItem(isbn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getItems() throws Exception {
        URL url = new URL(BASE_URL + "/items");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        System.out.println("Status: " + conn.getResponseCode());
        printAndDisconnect(conn);
    }

    private static String getRandomIsbn() throws Exception {
        URL url = new URL(BASE_URL + "/randomisbn");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String isbn = reader.readLine();
        reader.close();
        conn.disconnect();
        return isbn;
    }

    private static void createItem(String isbn, String title, String author) throws Exception {
        URL url = new URL(BASE_URL + "/items");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String json = String.format(Locale.US, "{\"isbn\": \"%s\", \"title\": \"%s\", \"author\": \"%s\"}", isbn, title, author);

        try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
            out.writeBytes(json);
            out.flush();
        }

        System.out.println("Status: " + conn.getResponseCode());
        printAndDisconnect(conn);
    }

    private static void updateItem(String isbn, String title, String author) throws Exception {
        URL url = new URL(BASE_URL + "/items");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String json = String.format(Locale.US, "{\"isbn\": \"%s\", \"title\": \"%s\", \"author\": \"%s\"}", isbn, title, author);

        try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
            out.writeBytes(json);
            out.flush();
        }

        System.out.println("Status: " + conn.getResponseCode());
        printAndDisconnect(conn);
    }

    private static void deleteItem(String isbn) throws Exception {
        // Usando o endpoint com "<isbn>" conforme o enunciado
        URL url = new URL(BASE_URL + "/items/" + isbn);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        System.out.println("Status: " + conn.getResponseCode());
        printAndDisconnect(conn);
    }

    private static void printAndDisconnect(HttpURLConnection connection) throws Exception {
        InputStream inputStream;
        int statusCode = connection.getResponseCode();

        if (statusCode >= 200 && statusCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        if (inputStream == null) {
            System.out.println("Resposta: (vazia)");
            connection.disconnect();
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        System.out.println("Resposta: " + response.toString());
        connection.disconnect();
    }
}