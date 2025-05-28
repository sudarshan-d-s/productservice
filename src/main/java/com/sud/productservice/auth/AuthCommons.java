package com.sud.productservice.auth;

import com.sud.productservice.dtos.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private final RestTemplate restTemplate;
    private final String userServiceValidateTokenUrl;

    public AuthCommons(RestTemplate restTemplate,
                       @Value("${user.auth.validatetoken.url}") String userServiceValidateTokenUrl) {
        this.restTemplate = restTemplate;
        this.userServiceValidateTokenUrl = userServiceValidateTokenUrl;
    }

    public UserDto validateToken(String token) {
        if(token == null) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Use exchange method to make the request with headers
        ResponseEntity<UserDto> response = restTemplate.exchange(
                userServiceValidateTokenUrl,
                HttpMethod.POST,
                entity,
                UserDto.class
        );

        return response.getBody();
    }

    @PostConstruct
    public void init() {
        if (userServiceValidateTokenUrl == null || userServiceValidateTokenUrl.isEmpty()) {
            throw new IllegalArgumentException("Property user.auth.validatetoken.url is not configured");
        }
    }

}
