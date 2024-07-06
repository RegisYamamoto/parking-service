package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.ParkingSpotDto;
import com.regis.parking_service.entity.ParkingSpot;
import com.regis.parking_service.repository.ParkingSpotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
                .id(UUID.randomUUID())
                .parkingSpotNumber(parkingSpotDto.parkingSpotNumber())
                .licensePlate(parkingSpotDto.licensePlate())
                .carBrand(parkingSpotDto.carBrand())
                .carModel(parkingSpotDto.carModel())
                .carColor(parkingSpotDto.carColor())
                .registrationDate(OffsetDateTime.now(ZoneId.of("UTC")))
                .responsibleName(parkingSpotDto.responsibleName())
                .apartment(parkingSpotDto.apartment())
                .block(parkingSpotDto.block())
                .build();
    }

    public Page<ParkingSpotDto> getAllParkingSpots(Pageable pageable) {
        Page<ParkingSpot> all = parkingSpotRepository.findAll(pageable);
        Page<ParkingSpotDto> parkingSpotDtos = mapEntityPageIntoDtoPage(all, ParkingSpotDto.class);

        return parkingSpotDtos;
    }

    public ParkingSpotDto getOneParkingSpot(UUID id) {
        Optional<ParkingSpot> parkingSpotOptional = parkingSpotRepository.findById(id);

        if (!parkingSpotOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Vaga não existe");
        }

        ParkingSpot parkingSpot = parkingSpotOptional.get();

        return new ParkingSpotDto(
                parkingSpot.getId(),
                parkingSpot.getParkingSpotNumber(),
                parkingSpot.getLicensePlate(),
                parkingSpot.getCarBrand(),
                parkingSpot.getCarModel(),
                parkingSpot.getCarColor(),
                parkingSpot.getRegistrationDate(),
                parkingSpot.getResponsibleName(),
                parkingSpot.getApartment(),
                parkingSpot.getBlock());
    }

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }

}
