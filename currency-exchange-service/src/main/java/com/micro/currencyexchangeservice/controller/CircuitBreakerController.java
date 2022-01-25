package com.micro.currencyexchangeservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {
        logger.info("Sample api called");
        ResponseEntity<String> entity = new RestTemplate().
                getForEntity("http://localhost:8080/not-exist-api", String.class);

        return entity.getBody();
    }

    public String hardcodedResponse(Exception ex) {
        return "fallback response";
    }
}
