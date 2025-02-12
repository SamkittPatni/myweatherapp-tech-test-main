package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  String address;

  @JsonProperty("description")
  String description;

  @JsonProperty("currentConditions")
  CurrentConditions currentConditions;

  @JsonProperty("days")
  List<Days> days;

  public CurrentConditions getCurrentConditions() {
    if (currentConditions == null) {
      currentConditions = new CurrentConditions();
    }
    return currentConditions;
  }

  public static class CurrentConditions {
    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("sunrise")
    String sunrise;

    @JsonProperty("sunset")
    String sunset;

    @JsonProperty("feelslike")
    String feelslike;

    @JsonProperty("humidity")
    String humidity;

    @JsonProperty("conditions")
    String conditions;

    public String getSunrise() {
      return sunrise;
    }

    public String getSunset() {
      return sunset;
    }
    public String getConditions() {
      return conditions;
    }

    public void setSunrise(String sr) {
      sunrise = sr;
    }

    public void setSunset(String ss) {
      sunset = ss;
    }
    public void setConditions(String c) {
      conditions = c;
    }
  }

  static class Days {

    @JsonProperty("datetime")
    String date;

    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("tempmax")
    String maxTemperature;

    @JsonProperty("tempmin")
    String minTemperature;

    @JsonProperty("conditions")
    String conditions;

    @JsonProperty("description")
    String description;

  }

}
