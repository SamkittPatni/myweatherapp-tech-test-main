package com.weatherapp.myweatherapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.weatherapp.myweatherapp.controller.WeatherController;
import com.weatherapp.myweatherapp.model.CityInfo;

class WeatherServiceTest {

  @Mock
  private WeatherService weatherService;

  @InjectMocks
  private WeatherController weatherController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCompareDaylight() {
    CityInfo city1 = new CityInfo();
    city1.getCurrentConditions().setSunrise("06:00");
    city1.getCurrentConditions().setSunset("18:00");

    CityInfo city2 = new CityInfo();
    city2.getCurrentConditions().setSunrise("07:00");
    city2.getCurrentConditions().setSunset("17:00");

    when(weatherService.forecastByCity("City1")).thenReturn(city1);
    when(weatherService.forecastByCity("City2")).thenReturn(city2);

    ResponseEntity<String> response = weatherController.compareDaylight("City1", "City2");
    assertTrue(response.getBody().contains("City1"));

  }

  @Test
  void testRainCheck() {
    CityInfo city1 = new CityInfo();
    city1.getCurrentConditions().setConditions("Rain");

    CityInfo city2 = new CityInfo();
    city2.getCurrentConditions().setConditions("Cloudy");

    when(weatherService.forecastByCity("City1")).thenReturn(city1);
    when(weatherService.forecastByCity("City2")).thenReturn(city2);

    ResponseEntity<String> response = weatherController.checkRain("City1", "City2");
    assertTrue(response.getBody().contains("It is raining in City1"));
  }
}