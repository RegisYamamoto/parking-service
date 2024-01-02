package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.ParkingSpotRequestDto;
import com.regis.parking_service.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ParkingSpotValidator {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public void validateRequest(ParkingSpotRequestDto parkingSpotRequestDto) {
        if (existsByLicensePlateCar(parkingSpotRequestDto.licensePlate())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ja existe a placa desse veiculo cadastrada no sistema");
        }
        if (existsByParkingSpotNumber(parkingSpotRequestDto.parkingSpotNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Essa vaga ja esta cadastrada para outro usuario");
        }
        if (existsByApartmentAndBlock(parkingSpotRequestDto.apartment(), parkingSpotRequestDto.block())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Essa vaga ja esta cadastrada para outro apartamento");
        }
    }

    public boolean existsByLicensePlateCar(String licensePlate) {
        return parkingSpotRepository.existsByLicensePlate(licensePlate);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

}
