package com.fatmadelenn.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class CarFactory {

    @Autowired
    private List<Car> cars;

    private static final HashMap<Class, Car> carHashMap = new HashMap<>();


    @PostConstruct
    public void initCarFactory() {
        for (Car car : cars) {
            carHashMap.put(car.getClass(), car);
        }
    }

    public static Car getCar(String name) {
        return carHashMap.get(carHashMap.keySet().stream()
                .filter(carClass -> carClass.getSimpleName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found")));
    }
}
