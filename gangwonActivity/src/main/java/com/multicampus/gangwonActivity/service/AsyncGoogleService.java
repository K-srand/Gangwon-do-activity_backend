package com.multicampus.gangwonActivity.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multicampus.gangwonActivity.entity.Tour4_0;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncGoogleService {

    @Async
    public CompletableFuture<Double> fetchRating(Tour4_0 ratelist, String apiKey) {
        String result = "";
        ObjectMapper objectMapper = new ObjectMapper();
        String placeTitle = "강원" + ratelist.getPlaceTitle();

        if (placeTitle == null || placeTitle.isEmpty()) {
            return CompletableFuture.completedFuture(null);
        }

        try {
            String googleApiUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="
                    + URLEncoder.encode(placeTitle, "UTF-8")
                    + "&inputtype=textquery&fields=formatted_address,name,rating,opening_hours,geometry&key="
                    + apiKey;

            URL url = new URL(googleApiUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();

            JsonNode jsonNode = objectMapper.readTree(result);
            JsonNode candidates = jsonNode.get("candidates");

            if (candidates.isArray() && !candidates.isEmpty()) {
                JsonNode candidate = candidates.get(0);
                if (candidate.has("rating")) {
                    return CompletableFuture.completedFuture(candidate.get("rating").asDouble());
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing Google API response for place: " + placeTitle + " Error: " + e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }
}
