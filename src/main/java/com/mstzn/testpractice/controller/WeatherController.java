package com.mstzn.testpractice.controller;

import com.mstzn.testpractice.client.WeatherClient;
import com.mstzn.testpractice.dto.response.WeatherResponse;
import com.mstzn.testpractice.exception.WeatherNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherClient weatherClient;

    @Autowired
    public WeatherController(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @GetMapping
    public String weather() {
        return weatherClient.fetchWeather()
                .map(WeatherResponse::getSummary)
                .orElseThrow(new WeatherNotFoundException());
    }

}
