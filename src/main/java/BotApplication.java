import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class BotApplication {
    public static void main(String[] args) throws IOException {

        var localProperties = new Properties();
        localProperties.load(new FileInputStream("src/main/resources/local.properties"));

        var url = "https://api.nasa.gov/planetary/apod?api_key=" + localProperties.getProperty("API_KEY");

        CloseableHttpClient client = HttpClients.createDefault();

        CloseableHttpResponse response = client.execute(new HttpGet(url));

        Scanner scanner = new Scanner(response.getEntity().getContent());

        System.out.println(scanner.nextLine());
    }
}
