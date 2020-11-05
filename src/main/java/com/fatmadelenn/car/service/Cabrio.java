package com.fatmadelenn.car.service;

import org.springframework.stereotype.Component;

@Component
public class Cabrio implements Car{

    @Override
    public String getType() {
        return "Cabrio Car has produced.";
    }
}
