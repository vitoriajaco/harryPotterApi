package vitoria.harryPotterApi.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import vitoria.harryPotterApi.entity.Hogwarts;
import vitoria.harryPotterApi.entity.Spell;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;


@org.springframework.stereotype.Service
public class HarryPotterApiClient {

    private final ObjectMapper objectMapper;

    private final HttpClient httpClient;

    public HarryPotterApiClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    }

    private <T> List<T> buscarLista(String url, TypeReference<List<T>> typeReference) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
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

            return objectMapper.readValue(httpResponse.body(), typeReference);


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consumir api", e);
        }
    }
    public List<Hogwarts> pegarTodosPersonagens() {
        return buscarLista("https://hp-api.onrender.com/api/characters", new TypeReference<List<Hogwarts>>() {
        });
    }
    public List<Hogwarts> todosEstudantes(){
        String url = "https://hp-api.onrender.com/api/characters/students";
        return  buscarLista(url, new TypeReference<List<Hogwarts>>() {
        });
    }

    public List<Hogwarts> allStaff(){
        return buscarLista("https://hp-api.onrender.com/api/characters/staff", new TypeReference<List<Hogwarts>>() {
        });

    }

    public List<Spell> allSpells(){
        return buscarLista("https://hp-api.onrender.com/api/spells", new TypeReference<List<Spell>>() {});

    }
}















