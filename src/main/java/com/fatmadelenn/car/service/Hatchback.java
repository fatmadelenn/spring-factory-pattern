package com.fatmadelenn.car.service;

import org.springframework.stereotype.Component;

@Component
public class Hatchback implements Car{

    @Override
    public String getType() {
        return "Hatchback Car has produced.";
    }
}
