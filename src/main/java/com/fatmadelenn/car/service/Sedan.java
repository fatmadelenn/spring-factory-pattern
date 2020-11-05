package com.fatmadelenn.car.service;

import org.springframework.stereotype.Component;

@Component
public class Sedan implements Car {

    @Override
    public String getType() {
        return "Sedan Car has produced.";
    }
}
