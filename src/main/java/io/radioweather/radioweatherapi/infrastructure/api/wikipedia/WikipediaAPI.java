package io.radioweather.radioweatherapi.infrastructure.api.wikipedia;

import io.radioweather.radioweatherapi.application.out.ImageStateAPIPort;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class WikipediaAPI implements ImageStateAPIPort {

    private final HttpClient httpClient;

    public WikipediaAPI() {
        this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();
    }

    public String urlImageStateAPIWithEval(String country, String state) {
        String url = urlImageStateAPI(country, state);
        if (url == null) {
            return null;
        }

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> resp =
                    httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            String body = resp.body() != null ? resp.body().trim() : "";

            boolean isHtml = body.toLowerCase().startsWith("<!doctype html>");
            boolean isXml  = body.startsWith("<?xml");

            if(isHtml) {
                return null;
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error evaluando el contenido de " + url, e);
        }

        return url;
    }


    @Override
    public String urlImageStateAPI(String country, String state) {
        String initialUrl = String.format(
                "https://commons.wikimedia.org/w/index.php?title=Special:Redirect/file/%s_%s_location_map.svg", country, state
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(initialUrl)).GET()
                .build();

        HttpResponse<String> response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response.statusCode() == 302 || response.statusCode() == 301) {
            return response.headers().firstValue("Location").orElse(null);
        }

        return null;
    }
}
