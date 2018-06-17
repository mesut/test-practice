package com.mstzn.testpractice.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mstzn.testpractice.helper.FileLoader;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WeatherResponseTest {

    @Test
    public void should_deserialize_json() throws Exception {
        String jsonResponse = FileLoader.read("classpath:weatherApiResponse.json");
        WeatherResponse expectedResponse = new WeatherResponse("Rain");

        WeatherResponse parsedResponse = new ObjectMapper().readValue(jsonResponse, WeatherResponse.class);

        assertThat(parsedResponse, is(expectedResponse));
    }
}