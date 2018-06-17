package com.mstzn.testpractice.client;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.mstzn.testpractice.dto.response.WeatherResponse;
import com.mstzn.testpractice.helper.FileLoader;
import org.apache.http.entity.ContentType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherClientConsumerTest {

    @Autowired
    private WeatherClient weatherClient;

    @Rule
    public PactProviderRuleMk2 weatherProvider = new PactProviderRuleMk2("weather_provider", "localhost", 8082, this);

    @Pact(consumer = "weather_consumer")
    public RequestResponsePact createPact(PactDslWithProvider pactDslWithProvider) throws IOException {
        return pactDslWithProvider.given("weather data")
                .uponReceiving("a request for a weather request istanbul")
                .path("/some-test-api-key/41.015137,28.979530")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(FileLoader.read("classpath:weatherApiResponse.json"), ContentType.APPLICATION_JSON)
                .toPact();

    }

    @Test
    @PactVerification("weather_provider")
    public void it_should_get_weather_information() {
        //given

        //when
        Optional<WeatherResponse> weatherResponse = weatherClient.fetchWeather();

        //then
        assertThat(weatherResponse.isPresent()).isTrue();
        assertThat(weatherResponse.get().getSummary()).isEqualTo("Rain");
    }
}