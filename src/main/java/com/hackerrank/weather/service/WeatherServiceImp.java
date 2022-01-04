package com.hackerrank.weather.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import com.hackerrank.weather.model.ApiResult;

@Service
public class WeatherServiceImp implements WeatherService{

  static final String SUCCESS = "Success";
  static final String WEATHER_NOT_FOUND = "Weather not found";
  static final String DATE="date";
  static final String _DATE="-date";

  @Autowired
  WeatherRepository repo;

  @Override
  public ResponseEntity<ApiResult> saveWeather(Weather weather){
    return new ResponseEntity<>(generateApiResult(String.valueOf(HttpStatus.CREATED.value()),SUCCESS,repo.save(weather)),HttpStatus.CREATED);

  }

  @Override
  public ResponseEntity<ApiResult> getWeather(Integer id) {
  	
	  Optional<Weather> weather = repo.findById(id);
	  if(weather.isPresent())
		  return  new ResponseEntity<>(generateApiResult(String.valueOf(HttpStatus.OK.value()),SUCCESS,weather.get()), HttpStatus.OK);
	  
	  return new ResponseEntity<>(generateApiResult(String.valueOf(HttpStatus.NOT_FOUND.value()),WEATHER_NOT_FOUND,null), HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<ApiResult> getWeatherByDate(Date date) {
  	return new ResponseEntity<>(generateApiResult(String.valueOf(HttpStatus.OK.value()),SUCCESS,repo.findByDate(date)), HttpStatus.OK);
  }

  ApiResult generateApiResult(String code, String message, Object data){
		return new ApiResult(code,message,data);
	}

@Override
public ResponseEntity<ApiResult> getWeatherByCities(String cities) {
	List<String> list = Arrays.asList(cities.split(","));
	return new ResponseEntity<>(generateApiResult(String.valueOf(HttpStatus.OK.value()),SUCCESS,repo.findByCityIgnoreCaseIn(list)), HttpStatus.OK);
}

@Override
public ResponseEntity<ApiResult> getWeatherBySort(String sort) {
	
	switch(sort){
	case DATE:
		return new ResponseEntity<>(generateApiResult(String.valueOf(HttpStatus.OK.value()),SUCCESS,repo.findAllByOrderByDateAsc()), HttpStatus.OK);
	case _DATE:
		return new ResponseEntity<>(generateApiResult(String.valueOf(HttpStatus.OK.value()),SUCCESS,repo.findAllByOrderByDateDesc()), HttpStatus.OK);
	default:
		return new ResponseEntity<>(generateApiResult(String.valueOf(HttpStatus.OK.value()),SUCCESS,repo.findAllByOrderByDateAsc()), HttpStatus.OK);
	}
	
	
}

@Override
public ResponseEntity<ApiResult> getWeather() {
	return new ResponseEntity<>(generateApiResult(String.valueOf(HttpStatus.OK.value()),SUCCESS,repo.findAllByOrderByIdAsc()), HttpStatus.OK);
}




}