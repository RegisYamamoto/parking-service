package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.ParkingSpotRequestDto;
import com.regis.parking_service.controller.dto.ParkingSpotResponseDto;
import com.regis.parking_service.entity.ParkingSpot;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class ParkingSpotService {

    public ParkingSpotResponseDto createNewParkingSpot(ParkingSpotRequestDto parkingSpotRequestDto) {
        ParkingSpot parkingSpot = parkingSpotFactory(parkingSpotRequestDto);

        // TODO salvar no banco

        return null;
    }

    public ParkingSpot parkingSpotFactory(ParkingSpotRequestDto parkingSpotRequestDto) {
        return ParkingSpot.builder()
                .id(UUID.randomUUID())
                .parkingSpotNumber(parkingSpotRequestDto.parkingSpotNumber())
                .licensePlate(parkingSpotRequestDto.licensePlate())
                .carBrand(parkingSpotRequestDto.carBrand())
                .carModel(parkingSpotRequestDto.carModel())
                .carColor(parkingSpotRequestDto.carColor())
                .registrationDate(OffsetDateTime.now())
                .responsibleName(parkingSpotRequestDto.responsibleName())
                .apartment(parkingSpotRequestDto.apartment())
                .block(parkingSpotRequestDto.block())
                .build();
    }

}
