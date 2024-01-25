import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.Scanner;

public class BotApplication {
    public static void main(String[] args) throws IOException {
        var url = "https://api.nasa.gov/planetary/apod?api_key=***REMOVED***";

        CloseableHttpClient client = HttpClients.createDefault();

        CloseableHttpResponse response = client.execute(new HttpGet(url));

        Scanner scanner = new Scanner(response.getEntity().getContent());

        System.out.println(scanner.nextLine());
    }
}
