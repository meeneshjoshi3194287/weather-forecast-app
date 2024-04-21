package com.assignment.weatherforecast.Model;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
public class WeatherModel {

    private Float latitude;
    private Float longitude;
    private List<String> current;
    private List<String> daily;
    private List<String> hourly;
    private String temperature_unit;
    private String timezone;
    private String start_date;
    private String end_date;
}
