package com.regis.parking_service.repository;

import com.regis.parking_service.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, UUID> {

    boolean existsByLicensePlate(String licensePlate);

    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    boolean existsByApartmentAndBlock(String apartment, String block);

    Optional<ParkingSpot> findByUuid(UUID uuid);

}
