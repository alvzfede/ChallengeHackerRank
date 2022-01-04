package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Weather;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer>, JpaSpecificationExecutor<Weather> {
	
	List<Weather> findByDate(Date date);
	List<Weather> findByCityIgnoreCaseIn(List<String> cities);
	List<Weather> findAllByOrderByDateAsc();
	List<Weather> findAllByOrderByDateDesc();
	List<Weather> findAllByOrderByIdAsc();
	
}
