package com.mstzn.testpractice.client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mstzn.testpractice.dto.response.WeatherResponse;
import com.mstzn.testpractice.helper.FileLoader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherClientIntegrationTest {

    @Autowired
    private WeatherClient weatherClient;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8082);

    @Test
    public void it_should_call_weather_service() throws IOException {
        //given
        wireMockRule.stubFor(get(urlPathEqualTo("/some-test-api-key/41.015137,28.979530"))
                .willReturn(aResponse()
                        .withBody(FileLoader.read("classpath:weatherApiResponse.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        //when
        Optional<WeatherResponse> weatherResponse = weatherClient.fetchWeather();

        //then
        assertThat(weatherResponse).isEqualTo(Optional.of(new WeatherResponse("Rain")));
    }
}