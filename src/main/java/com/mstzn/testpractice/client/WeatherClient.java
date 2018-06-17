package com.mstzn.testpractice.client;

import com.mstzn.testpractice.dto.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class WeatherClient {

    private static final String LATITUDE = "41.015137";
    private static final String LONGITUDE = "28.979530";
    private final RestTemplate restTemplate;
    private final String weatherServiceUrl;
    private final String weatherServiceApiKey;

    @Autowired
    public WeatherClient(final RestTemplate restTemplate,
                         @Value("${weather.url}") final String weatherServiceUrl,
                         @Value("${weather.api_secret}") final String weatherServiceApiKey) {
        this.restTemplate = restTemplate;
        this.weatherServiceUrl = weatherServiceUrl;
        this.weatherServiceApiKey = weatherServiceApiKey;
    }

    public Optional<WeatherResponse> fetchWeather() {
        String url = String.format("%s/%s/%s,%s", weatherServiceUrl, weatherServiceApiKey, LATITUDE, LONGITUDE);

        try {
            return Optional.ofNullable(restTemplate.getForObject(url, WeatherResponse.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
