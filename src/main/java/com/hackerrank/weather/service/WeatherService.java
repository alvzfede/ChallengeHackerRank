package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.ApiResult;
import org.springframework.http.ResponseEntity;
import java.util.Date;

public interface WeatherService{

  ResponseEntity<ApiResult> saveWeather(Weather weather);
  
  ResponseEntity<ApiResult> getWeather(Integer id);
  
  ResponseEntity<ApiResult> getWeatherByDate(Date date);

  ResponseEntity<ApiResult> getWeatherByCities(String cities);
  
  ResponseEntity<ApiResult> getWeatherBySort(String sort);
  
  ResponseEntity<ApiResult> getWeather();

}