package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.ParkingSpotDto;
import com.regis.parking_service.entity.ParkingSpot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ParkingSpotServiceTest {

    @InjectMocks
    private ParkingSpotService parkingSpotService;

    @Test
    public void test2() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        Method[] a = parkingSpotService.getClass().getDeclaredMethods();

        for (Method m : a) {
            if (m.getName().equals("parkingSpotFactory")) {
                m.setAccessible(true);
                ParkingSpotDto parkingSpotDto = new ParkingSpotDto();
                parkingSpotDto.setId(UUID.randomUUID());
                ParkingSpot response = (ParkingSpot) m.invoke(parkingSpotService, parkingSpotDto);
                Assertions.assertNotNull(response.getId());
                System.out.println(m.getName());
            }
        }
    }

}
