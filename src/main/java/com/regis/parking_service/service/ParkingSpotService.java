package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.ParkingSpotRequestDto;
import com.regis.parking_service.controller.dto.ParkingSpotResponseDto;
import com.regis.parking_service.entity.ParkingSpot;
import com.regis.parking_service.repository.ParkingSpotRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotValidator parkingSpotValidator;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotRequestDto createNewParkingSpot(ParkingSpotRequestDto parkingSpotRequestDto) {
        parkingSpotValidator.validateRequest(parkingSpotRequestDto);

        ParkingSpot parkingSpot = parkingSpotFactory(parkingSpotRequestDto);

        parkingSpotRepository.save(parkingSpot);

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
                .registrationDate(OffsetDateTime.now(ZoneId.of("UTC")))
                .responsibleName(parkingSpotRequestDto.responsibleName())
                .apartment(parkingSpotRequestDto.apartment())
                .block(parkingSpotRequestDto.block())
                .build();
    }

}
