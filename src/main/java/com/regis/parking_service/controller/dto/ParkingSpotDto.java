package com.regis.parking_service.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotDto {
    private Long id;
    private UUID uuid;
    @NotNull @NotBlank
    private String parkingSpotNumber;
    @NotNull @NotBlank
    private String licensePlate;
    @NotNull @NotBlank
    private String carBrand;
    @NotNull @NotBlank
    private String carModel;
    @NotNull @NotBlank
    private String carColor;
    private OffsetDateTime registrationDate;
    @NotNull @NotBlank
    private String responsibleName;
    private String apartment;
    private String block;
}
