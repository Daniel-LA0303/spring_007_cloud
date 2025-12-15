package com.mx.mcsv.service.user.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.mcsv.service.user.model.Bike;

@FeignClient(name = "service-bike")
public interface BikeFeignClient {

	@GetMapping("/bike/byuser/{userId}")
	List<Bike> getBikes(@PathVariable("userId") int userId);

	@PostMapping("/bike")
	Bike save(@RequestBody Bike bike);
}