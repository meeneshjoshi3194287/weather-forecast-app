package com.assignment.weatherforecast.controller;

import com.assignment.weatherforecast.Model.WeatherModel;
import com.assignment.weatherforecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(("v1/weather-forecast-api"))
public class WeatherForecastController {

    private final WeatherForecastService weatherForecastService;

    @Autowired
    public WeatherForecastController(WeatherForecastService weatherForecastService){
        this.weatherForecastService=weatherForecastService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/fetch-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Object> fetchWeatherForecast(@RequestParam String latitude,@RequestParam String longitude,@RequestParam(required = false) List<String> current,@RequestParam(required = false) List<String>hourly,@RequestParam(required = false) List<String> daily,@RequestParam(required = false) String temperature_unit,@RequestParam(required = false) String timezone,@RequestParam(required = false) String start_date,@RequestParam(required = false) String end_date) {
        if(current!=null && current.isEmpty()){
            current.add("temperature_2m");
            current.add("wind_speed_10m");
        }
        if(hourly!=null && hourly.isEmpty()){
            hourly.add("temperature_2m");
            hourly.add("relative_humidity_2m");
            hourly.add("apparent_temperature");
            hourly.add("precipitation_probability");
            hourly.add("precipitation");
        }
        if(daily!=null && daily.isEmpty()){
            daily.add("temperature_2m_max");
            daily.add("temperature_2m_min");
            daily.add("apparent_temperature_max");
            daily.add("apparent_temperature_min");
            daily.add("precipitation_sum");
            daily.add("precipitation_probability_max");
        }
        if(timezone==null || timezone.isEmpty()) {
            timezone = "auto";
        }
        if(temperature_unit==null || temperature_unit.isEmpty()){
            temperature_unit="celsius";
        }
        if(start_date==null || start_date.isEmpty()){
            start_date = String.valueOf(LocalDate.now());
        }
        if(end_date==null || end_date.isEmpty()){
            end_date = String.valueOf(LocalDate.now().plusDays(6));
        }

        return weatherForecastService.fetchWeatherForcast(Float.valueOf(latitude), Float.valueOf(longitude),current, daily, hourly, temperature_unit, timezone, start_date, end_date);
    }

}
