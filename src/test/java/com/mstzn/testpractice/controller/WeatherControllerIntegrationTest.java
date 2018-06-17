package com.mstzn.testpractice.controller;

import com.mstzn.testpractice.client.WeatherClient;
import com.mstzn.testpractice.dto.response.WeatherResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WeatherController.class)
public class WeatherControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WeatherClient weatherClient;

    @Test
    public void it_should_get_weather() throws Exception {
        //given
        when(weatherClient.fetchWeather()).thenReturn(Optional.of(new WeatherResponse("cloudy")));

        //when
        mockMvc.perform(get("/weather"))
                .andExpect(content().string("cloudy"))
                .andExpect(status().isOk());

    }

    @Test
    public void it_should_get_404_when_weather_not_found() throws Exception {
        //given
        when(weatherClient.fetchWeather()).thenReturn(Optional.empty());

        //when
        mockMvc.perform(get("/weather"))
                .andExpect(status().isNotFound());
    }
}