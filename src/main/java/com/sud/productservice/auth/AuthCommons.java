package com.sud.productservice.auth;

import com.sud.productservice.dtos.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
                "http://UserService/users/validate",
                HttpMethod.POST,
                entity,
                UserDto.class
        );

        return response.getBody();
    }

}
