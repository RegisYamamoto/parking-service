package com.regis.parking_service.controller;

import com.regis.parking_service.controller.dto.ParkingSpotRequestDto;
import com.regis.parking_service.controller.dto.ParkingSpotResponseDto;
import com.regis.parking_service.service.ParkingSpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://example.com", maxAge = 3600)
@RequestMapping(value = "/parking-spots")
@Tag(name = "parking-spot-api")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping
    @Operation(summary = "Realiza o cadastro de vagas de estacionamento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vaga do estacionamento cadastrada com sucesso")
    })
    public ResponseEntity saveParkingSpot(
            @RequestBody ParkingSpotRequestDto parkingSpotRequestDto) {

        ParkingSpotRequestDto response = parkingSpotService.createNewParkingSpot(parkingSpotRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
