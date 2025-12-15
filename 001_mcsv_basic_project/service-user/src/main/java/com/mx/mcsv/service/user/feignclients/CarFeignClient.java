package com.mx.mcsv.service.user.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.mcsv.service.user.model.Car;

@FeignClient(name = "service-car")
public interface CarFeignClient {

	@GetMapping("/car/byuser/{userId}")
	List<Car> getCars(@PathVariable("userId") int userId);

	@PostMapping("/car")
	Car save(@RequestBody Car car);
}
