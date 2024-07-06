package com.regis.parking_service.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ParkingSpotDto(
        UUID id,
        @NotNull @NotBlank String parkingSpotNumber,
        @NotNull @NotBlank String licensePlate,
        @NotNull @NotBlank String carBrand,
        @NotNull @NotBlank String carModel,
        @NotNull @NotBlank String carColor,
        OffsetDateTime registrationDate,
        @NotNull @NotBlank String responsibleName,
        String apartment,
        String block
) {
    public ParkingSpotDto(UUID id) {
        this(id, null, null, null, null,
                null, null, null, null, null);
    }
}
