package com.fatmadelenn.car.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    public String getType(String name) {
        Car car = CarFactory.getCar(name);
        logger.info("Result : {}", car.getType());
        return car.getType();
    }
}
