package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.ParkingSpotDto;
import com.regis.parking_service.entity.ParkingSpot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

@SpringBootTest
public class ParkingSpotServiceTest {

    @InjectMocks
    private ParkingSpotService parkingSpotService;

    @Test
    public void itShouldCreateParkingSpotObject() throws InvocationTargetException, IllegalAccessException {
        // Foi preciso usar a lib reflect para poder testar o método privado parkingSpotFactory.

        // Pega todos os métodos da classe ParkingSpotService
        Method[] a = parkingSpotService.getClass().getDeclaredMethods();

        for (Method m : a) {
            // Se o método for igual a parkingSpotFactory, entra no if
            if (m.getName().equals("parkingSpotFactory")) {
                m.setAccessible(true);
                ParkingSpotDto parkingSpotDto = ParkingSpotDto.builder()
                        .uuid(UUID.randomUUID())
                        .build();

                // Chama o método ParkingSpotService.parkingSpotFactory passando o parkingSpotDto como parametro
                ParkingSpot response = (ParkingSpot) m.invoke(parkingSpotService, parkingSpotDto);
                Assertions.assertNotNull(response.getUuid());
            }
        }
    }

}
