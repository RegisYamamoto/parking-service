package com.regis.parking_service.service;

import org.springframework.stereotype.Component;

@Component
public class ParkingSpotValidator {

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return false;
    }

}
