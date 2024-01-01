package com.regis.parking_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parking_spot")
public class ParkingSpot {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private UUID id;

    @Column(name = "parking_spot_number", nullable = false, length = 10)
    private String parkingSpotNumber;

    @Column(name = "license_plate", nullable = false, length = 7)
    private String licensePlate;

    @Column(name = "car_brand", nullable = false, length = 70)
    private String carBrand;

    @Column(name = "car_model", nullable = false, length = 70)
    private String carModel;

    @Column(name = "car_color", nullable = false, length = 70)
    private String carColor;

    @Column(name = "registration_date", nullable = false)
    private OffsetDateTime registrationDate;

    @Column(name = "responsible_name", nullable = false, length = 130)
    private String responsibleName;

    @Column(name = "apartment", length = 30)
    private String apartment;

    @Column(name = "block", length = 30)
    private String block;

}
