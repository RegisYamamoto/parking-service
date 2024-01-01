package com.regis.parking_service.controller.dto;

import java.time.OffsetDateTime;

public record ParkingSpotRequestDto(
        String parkingSpotNumber,
        String licensePlate,
        String carBrand,
        String carModel,
        String carColor,
        String responsibleName,
        String apartment,
        String block) {
}
