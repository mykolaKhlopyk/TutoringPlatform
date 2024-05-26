package com.mkh.tutoringplatform.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping
    public String startEndpoint() {
        return "redirect:/courses";
    }

    private String clientId = "fUNUHBA6SAeaBCUhN7sNQ";
    private String clientSecret = "0EDESrv4H02jjH1V1apDEdOUU9ECe5is";

    @GetMapping("/test")
    public String test() {
        String credentials = clientId + ":" + clientSecret;
        String base64Credentials = new String(Base64.getEncoder().encode(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = new RestTemplate().exchange(
                "https://zoom.us/oauth/token",
                HttpMethod.POST,
                entity,
                String.class);

        String accessToken = (response.getBody());

        ////

        HttpHeaders headers2 = new HttpHeaders();
        headers2.setBearerAuth(accessToken);
        headers2.setContentType(MediaType.APPLICATION_JSON);


        String meetingDetails = "{\"topic\": \"Example Meeting\", \"type\": 2\", \"start_time\": 2024-06-01T15:00:00\", \"duration\": 30\", \"timezone\": Europe/Kiev\", \"agenda\": Обговорення проекту}";
        HttpEntity<String> entity2 = new HttpEntity<>(meetingDetails, headers2);

        ResponseEntity<String> meetingResponse = new RestTemplate().exchange(
                "https://api.zoom.us/v2/users/me/meetings",
                HttpMethod.POST,
                entity2,
                String.class);

        System.out.println(meetingResponse.getBody());
        return "redirect:/courses";
    }
}
