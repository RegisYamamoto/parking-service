package com.regis.parking_service.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ParkingSpotDto {
    private UUID id;
    @NotNull
    @NotBlank
    private String parkingSpotNumber;
    @NotNull
    @NotBlank
    private String licensePlate;
    @NotNull
    @NotBlank
    private String carBrand;
    @NotNull
    @NotBlank
    private String carModel;
    @NotNull
    @NotBlank
    private String carColor;
    private OffsetDateTime registrationDate;
    @NotNull
    @NotBlank
    private String responsibleName;
    private String apartment;
    private String block;
}
