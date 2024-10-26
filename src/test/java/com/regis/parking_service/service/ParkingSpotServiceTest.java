package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.ParkingSpotDto;
import com.regis.parking_service.entity.ParkingSpot;
import com.regis.parking_service.repository.ParkingSpotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ParkingSpotServiceTest {

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @Mock
    private ParkingSpotValidator parkingSpotValidator;

    @InjectMocks
    private ParkingSpotService parkingSpotService;

    private ParkingSpotDto parkingSpotDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        parkingSpotDto = new ParkingSpotDto();
        // Configurar os atributos de parkingSpotDto conforme necessário
        parkingSpotDto.setParkingSpotNumber("A1");
        parkingSpotDto.setLicensePlate("ABC-1234");
        parkingSpotDto.setCarBrand("Toyota");
        parkingSpotDto.setCarModel("Corolla");
        parkingSpotDto.setCarColor("Preto");
        parkingSpotDto.setResponsibleName("João");
        parkingSpotDto.setApartment("101");
        parkingSpotDto.setBlock("B");
    }

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

    @Test
    public void testCreateNewParkingSpot_Success() {
        // Configurações do mock
        doNothing().when(parkingSpotValidator).validateRequest(parkingSpotDto);

        // Chama o método a ser testado
        ResponseEntity<?> response = parkingSpotService.createNewParkingSpot(parkingSpotDto);

        // Verificações
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(parkingSpotRepository, times(1)).save(any(ParkingSpot.class));
    }

    @Test
    public void testCreateNewParkingSpot_ValidationFailed() {
        // Configurações do mock para simular falha na validação
        doThrow(new IllegalArgumentException("Validação falhou")).when(parkingSpotValidator).validateRequest(parkingSpotDto);

        // Verifica se a exceção é lançada
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            parkingSpotService.createNewParkingSpot(parkingSpotDto);
        });

        assertEquals("Validação falhou", exception.getMessage());
        verify(parkingSpotRepository, never()).save(any(ParkingSpot.class));
    }

}
