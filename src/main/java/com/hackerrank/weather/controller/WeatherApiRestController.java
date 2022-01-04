package com.hackerrank.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.validation.Valid;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.WeatherService;
import com.hackerrank.weather.model.ApiResult;

@RestController
@RequestMapping("weather")
public class WeatherApiRestController {

  @Autowired
  WeatherService service;

  @PostMapping
  public ResponseEntity<ApiResult> saveWeather(@Valid @RequestBody Weather weather){
		return service.saveWeather(weather);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResult> getWeather(@PathVariable(value = "id") Integer id){
		return service.getWeather(id);
  }
  
  @GetMapping()
  public ResponseEntity<ApiResult> getWeatherByDate(@RequestParam(name="date",required=false)String date,
		  											@RequestParam(name="city",required=false )String city,
		  											@RequestParam(name="sort",required=false )String sort) throws ParseException{
	  
	  if(!ObjectUtils.isEmpty(date)){
		  Date newDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
		  return service.getWeatherByDate(newDate);  
	  }
	  if(!ObjectUtils.isEmpty(city)){
		  return service.getWeatherByCities(city);
	  }
	  if(!ObjectUtils.isEmpty(sort)){
		  return service.getWeatherBySort(sort);
	  }
	  return service.getWeather();
  }
  

  
  
}
