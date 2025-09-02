package com.resume.project.moderator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ModerationService {

    // This will inject the API key from our configuration file
    @Value("${google.api.key}")
    private String apiKey;

   public String moderateText(String inputText) {
    RestTemplate restTemplate = new RestTemplate();
    String apiUrl = "https://language.googleapis.com/v1beta2/documents:moderateText?key=" + apiKey;

    Map<String, Object> document = Map.of("type", "PLAIN_TEXT", "content", inputText);
    Map<String, Object> requestBody = Map.of("document", document);

    try {
        Map<String, Object> response = restTemplate.postForObject(apiUrl, requestBody, Map.class);

        if (response != null && response.get("moderationCategories") instanceof java.util.List) {
            java.util.List<Map<String, Object>> categories = (java.util.List<Map<String, Object>>) response.get("moderationCategories");

            // Loop through each category the AI found
            for (Map<String, Object> category : categories) {
                // Get the confidence score as a number
                double confidence = ((Number) category.get("confidence")).doubleValue();

                // Only reject if the AI is more than 70% confident it's bad content
                if (confidence > 0.7) {
                    System.out.println("Decision: REJECT due to category '" + category.get("name") + "' with confidence " + confidence);
                    return "REJECT";
                }
            }
        }
        
        System.out.println("Decision: APPROVE");
        return "APPROVE";

    } catch (Exception e) {
        System.err.println("Error calling Google AI: " + e.getMessage());
        return "APPROVE";
    }
}
}