package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.ParkingSpotDto;
import com.regis.parking_service.entity.ParkingSpot;
import com.regis.parking_service.repository.ParkingSpotRepository;
import com.regis.parking_service.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotValidator parkingSpotValidator;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public ResponseEntity createNewParkingSpot(ParkingSpotDto parkingSpotDto) {
        parkingSpotValidator.validateRequest(parkingSpotDto);

        ParkingSpot parkingSpot = parkingSpotFactory(parkingSpotDto);

        parkingSpotRepository.save(parkingSpot);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private ParkingSpot parkingSpotFactory(ParkingSpotDto parkingSpotDto) {
        return ParkingSpot.builder()
                .uuid(UUID.randomUUID())
                .parkingSpotNumber(parkingSpotDto.getParkingSpotNumber())
                .licensePlate(parkingSpotDto.getLicensePlate())
                .carBrand(parkingSpotDto.getCarBrand())
                .carModel(parkingSpotDto.getCarModel())
                .carColor(parkingSpotDto.getCarColor())
                .registrationDate(OffsetDateTime.now(ZoneId.of("UTC")))
                .responsibleName(parkingSpotDto.getResponsibleName())
                .apartment(parkingSpotDto.getApartment())
                .block(parkingSpotDto.getBlock())
                .build();
    }

    public Page<ParkingSpotDto> getAllParkingSpots(Pageable pageable) {
        Page<ParkingSpot> parkingSpotList = parkingSpotRepository.findAll(pageable);
        Page<ParkingSpotDto> parkingSpotDtos = Utils.mapEntityPageIntoDtoPage(parkingSpotList, ParkingSpotDto.class);
        return parkingSpotDtos;
    }

    public ParkingSpotDto getOneParkingSpot(@PathVariable UUID uuid) {
        Optional<ParkingSpot> parkingSpotOptional = parkingSpotRepository.findByUuid(uuid);

        if (!parkingSpotOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Vaga não existe");
        }

        ParkingSpot parkingSpot = parkingSpotOptional.get();

        return ParkingSpotDto.builder()
                .uuid(parkingSpot.getUuid())
                .parkingSpotNumber(parkingSpot.getParkingSpotNumber())
                .licensePlate(parkingSpot.getLicensePlate())
                .carBrand(parkingSpot.getCarBrand())
                .carModel(parkingSpot.getCarModel())
                .carColor(parkingSpot.getCarColor())
                .registrationDate(parkingSpot.getRegistrationDate())
                .responsibleName(parkingSpot.getResponsibleName())
                .apartment(parkingSpot.getApartment())
                .block(parkingSpot.getBlock())
                .build();
    }



}
