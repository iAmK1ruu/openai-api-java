
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

public class Main {

    public static String callAPI(String prompt) {
        String url = "URL PROVIDER", apiKey = "YOUR API HERE", model = "THE SPECIFIC MODEL HERE";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");


            /*
             * Request body 
             * {"model": "<model>", "messages": [{"role": "user", "content": "<prompt>"}]};
             */
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            // Allow the LLM to output from the text we provided
            connection.setDoOutput(true);
            // Write the body onto the prompt
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Get the response from the LLM
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            return extractMessage(response.toString());
        } catch (Exception e) {
            if (String.valueOf(e).contains("401")) {
                System.out.println("The API key is disabled/invalid");
            } else if (String.valueOf(e).contains("429")) {
                System.out.println("The provider cannot process the request at this time.");
            } else if(String.valueOf(e).contains("no protocol")) {
                System.out.println("Invalid Provider URL");
            }
            throw new RuntimeException(e);
        }
    }

    public static String extractMessage(String response) {
        int start = response.indexOf("content") + 10;
        int end = response.indexOf("\"", start);

        String result = response.substring(start, end);
        return result;

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Prompt:");
        String prompt = scan.nextLine();
        System.out.println(callAPI(prompt));
    }
}
