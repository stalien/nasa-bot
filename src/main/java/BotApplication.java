import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class BotApplication {
    public static void main(String[] args) throws IOException {

        Properties localProperties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/local.properties")) {
            localProperties.load(fis);
        }

        String apiKey = localProperties.getProperty("API_KEY");
        String url = String.format("https://api.nasa.gov/planetary/apod?api_key=%s&date=%s",
                apiKey,
                "2024-01-26"
        );

//        System.out.println(url);

        CloseableHttpClient client = HttpClients.createDefault();

        CloseableHttpResponse response = client.execute(new HttpGet(url));

//        Scanner scanner = new Scanner(response.getEntity().getContent());
//        System.out.println(scanner.nextLine());

        ObjectMapper mapper = new ObjectMapper();
        NasaAnswer answer = mapper.readValue(response.getEntity().getContent(), NasaAnswer.class);

        CloseableHttpResponse image = client.execute(new HttpGet(answer.url));
        String filename = answer.url.split("/")[answer.url.split("/").length - 1];
        FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/img/" + filename);
        fileOutputStream.write(image.getEntity().getContent().readAllBytes());

    }
}
