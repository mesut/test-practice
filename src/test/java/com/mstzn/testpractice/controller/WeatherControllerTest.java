package com.mstzn.testpractice.controller;

import com.mstzn.testpractice.client.WeatherClient;
import com.mstzn.testpractice.dto.response.WeatherResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    @InjectMocks
    private WeatherController weatherController;

    @Mock
    private WeatherClient weatherClient;

    @Test
    public void it_should_get_weather() {
        //given
        WeatherResponse weatherResponse = new WeatherResponse("Istanbul Cloudy");

        when(weatherClient.fetchWeather()).thenReturn(Optional.of(weatherResponse));

        //when
        String weather = weatherController.weather();

        //then
        assertThat(weather).isEqualTo("Istanbul Cloudy");
    }

    @Test
    public void it_should_retun_error_message_when_weather_not_found() {
        //given
        when(weatherClient.fetchWeather()).thenReturn(Optional.empty());

        //when
        String weather = weatherController.weather();

        //then
        assertThat(weather).isEqualTo("Could not fetch weather");
    }
}