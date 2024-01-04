package com.regis.parking_service.controller;

import com.regis.parking_service.controller.dto.ParkingSpotDto;
import com.regis.parking_service.service.ParkingSpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
@RequestMapping(value = "/parking-spots")
@Tag(name = "parking-spot-api")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping
    @Operation(summary = "Realiza o cadastro de vagas de estacionamento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vaga do estacionamento cadastrada com sucesso"),
            @ApiResponse(responseCode = "409", description =
                    "Ja existe a placa desse veiculo cadastrada no sistema ou Essa vaga ja esta cadastrada para" +
                            "outro usuario ou Essa vaga ja esta cadastrada para outro apartamento")
    })
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

}
