package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.ParkingSpotRequestDto;
import com.regis.parking_service.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotValidator {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public void validateRequest(ParkingSpotRequestDto parkingSpotRequestDto) {
        if (existsByLicensePlateCar(parkingSpotRequestDto.licensePlate())) {
            // TODO lançar exception
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Licence Plate Car is already in use");
        }
        if (existsByParkingSpotNumber(parkingSpotRequestDto.parkingSpotNumber())) {
            // TODO lançar exception
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use");
        }
        if (existsByApartmentAndBlock(parkingSpotRequestDto.apartment(), parkingSpotRequestDto.block())) {
            // TODO lançar exception
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartament/block");
        }
    }

    public boolean existsByLicensePlateCar(String licensePlate) {
        boolean b = parkingSpotRepository.existsByLicensePlate(licensePlate);
        return parkingSpotRepository.existsByLicensePlate(licensePlate);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

}
