package vitoria.harryPotterApi.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import vitoria.harryPotterApi.entity.Hogwarts;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10)).build();

    public List<Hogwarts> pegarTodosPersonagens() throws IOException, InterruptedException {
        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://hp-api.onrender.com/api/characters"))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() != 200) {
                throw new RuntimeException("Erro ao buscar endereço http: " + httpResponse.statusCode());
            }

            String contentType = httpResponse.headers().firstValue("Content-Type").orElse("");
            if (!contentType.contains("application/json")) {
                throw new RuntimeException("Resposta nao e json. Content Type: " + contentType);
            }

            return objectMapper.readValue(httpResponse.body(), new TypeReference<List<Hogwarts>>() {
            });


        } catch (IOException | InterruptedException e){
            throw new RuntimeException("Erro ao consumir api", e);
        }
    }
}









