package com.fatmadelenn.car.controller;

import com.fatmadelenn.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "api/car/{name}")
    public String getType(@PathVariable String name) {
        return carService.getType(name);
    }

}
