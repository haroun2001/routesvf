/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.routesvf;

/**
 *
 * @author PC
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RouteController {
 @Value("${openrouteservice.api.key}")
    private String apiKey;

    @PostMapping("/route")
    public ResponseEntity<?> calculateRoute(@RequestBody Map<String, Object> request) {
        // Récupérer les coordonnées du frontend
        Map<String, Object> start = (Map<String, Object>) request.get("start");
        Map<String, Object> end = (Map<String, Object>) request.get("end");

        double startLat = (double) start.get("lat");
        double startLon = (double) start.get("lon");
        double endLat = (double) end.get("lat");
        double endLon = (double) end.get("lon");

        // Construire l'URL de la requête vers OpenRouteService
        String url = "https://api.openrouteservice.org/v2/directions/driving-car?" +
                     "api_key=" + apiKey + 
                     "&start=" + startLon + "," + startLat + 
                     "&end=" + endLon + "," + endLat;

        // Envoyer la requête avec RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Retourner la réponse à Angular
        return ResponseEntity.ok(response.getBody());
    }
}
