package com.regis.parking_service.controller;

import com.regis.parking_service.controller.dto.ParkingSpotDto;
import com.regis.parking_service.service.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
@RequestMapping(value = "/parking-spots")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping
    public ResponseEntity saveParkingSpot(
            @RequestBody @Valid ParkingSpotDto parkingSpotRequestDto) {

        parkingSpotService.createNewParkingSpot(parkingSpotRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpotDto>> getAllParkingSpots(
            @PageableDefault(page = 0, size = 10, sort = "registrationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.getAllParkingSpots(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParkingSpotDto> getOneParkingSpot(@PathVariable UUID id) {
        ParkingSpotDto parkingSpotDto = parkingSpotService.getOneParkingSpot(id);
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotDto);
    }
}
