package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/weather")
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }

  // TODO: given two city names, compare the length of the daylight hours and return the city with the longest day
  @GetMapping("/comparedaylight")
  public ResponseEntity<String> compareDaylight(@RequestParam String city1, @RequestParam String city2) {
    try {
      CityInfo cityInfo1 = forecastByCity(city1).getBody();
      CityInfo cityInfo2 = forecastByCity(city2).getBody();

      long daylight1 = Integer.parseInt(cityInfo1.getCurrentConditions().getSunset().split(":")[0]) - Integer.parseInt(cityInfo1.getCurrentConditions().getSunrise().split(":")[0]);
      long daylight2 = Integer.parseInt(cityInfo2.getCurrentConditions().getSunset().split(":")[0]) - Integer.parseInt(cityInfo2.getCurrentConditions().getSunrise().split(":")[0]);

      String result = (daylight1 > daylight2) ? city1 : city2;
      return ResponseEntity.ok("City with the longest daylight hours: " + result);
}
    catch (Exception e) {
      return ResponseEntity.badRequest().body("Error fetching daylight data: " + e.getMessage());
    }
  }
  // TODO: given two city names, check which city its currently raining in

  @GetMapping("/rain-check")
    public ResponseEntity<String> checkRain(@RequestParam String city1, @RequestParam String city2) {
      try {
        CityInfo cityInfo1 = forecastByCity(city1).getBody();
        CityInfo cityInfo2 = forecastByCity(city2).getBody();

        boolean isRainingCity1 = cityInfo1.getCurrentConditions().getConditions().toLowerCase().contains("rain");
        boolean isRainingCity2 = cityInfo2.getCurrentConditions().getConditions().toLowerCase().contains("rain");

        if (isRainingCity1 && isRainingCity2) {
            return ResponseEntity.ok("It is raining in both " + city1 + " and " + city2);
        } else if (isRainingCity1) {
            return ResponseEntity.ok("It is raining in " + city1);
        } else if (isRainingCity2) {
            return ResponseEntity.ok("It is raining in " + city2);
        } else {
            return ResponseEntity.ok("It is not raining in either city.");
        }
      } catch (Exception e) {
          return ResponseEntity.badRequest().body("Error fetching rain data: " + e.getMessage());
      }
    }

}
