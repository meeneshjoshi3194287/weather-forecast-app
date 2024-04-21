package com.assignment.weatherforecast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WeatherForecastService {

    private static final String API_URL = "https://api.open-meteo.com/";
    private final WebClient webClient;

    @Autowired
    public WeatherForecastService(WebClient.Builder webCLientBuilder) {
        this.webClient = webCLientBuilder.baseUrl(API_URL).build();
    }

    public Mono<Object> fetchWeatherForcast(Float latitude, Float longitude, List<String> current, List<String> daily,List<String>hourly,String temperature_unit,String timezone,String start_date,String end_date) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/v1/forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("current", current)
                        .queryParam("daily", daily)
                        .queryParam("hourly",hourly)
                        .queryParam("temperature_unit",temperature_unit)
                        .queryParam("timezone",timezone)
                        .queryParam("start_date",start_date)
                        .queryParam("end_date",end_date)
                        .build()).retrieve()
                .bodyToMono(Object.class);
    }
}
