package com.mstzn.testpractice.client;

import com.mstzn.testpractice.dto.response.WeatherResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class WeatherClientTest {

    private WeatherClient weatherClient;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        initMocks(this);
        weatherClient = new WeatherClient(restTemplate, "http://localhost:8089", "someAppId");
    }

    @Test
    public void it_should_get_weather_from_weather_service() {
        //given
        WeatherResponse expectedResponse = new WeatherResponse("light rain");
        when(restTemplate.getForObject("http://localhost:8089/someAppId/41.015137,28.979530", WeatherResponse.class)).thenReturn(expectedResponse);

        //when
        Optional<WeatherResponse> weatherResponse = weatherClient.fetchWeather();

        //then
        assertThat(weatherResponse).isEqualTo(Optional.of(expectedResponse));
    }

    @Test
    public void it_should_return_empty_when_exception_occurred() {
        //given
        when(restTemplate.getForObject("http://localhost:8089/someAppId/41.015137,28.979530", WeatherResponse.class))
                .thenThrow(new RestClientException("Some thing went wrong"));

        //when
        Optional<WeatherResponse> weatherResponse = weatherClient.fetchWeather();

        //then
        assertThat(weatherResponse).isEqualTo(Optional.empty());
    }
}