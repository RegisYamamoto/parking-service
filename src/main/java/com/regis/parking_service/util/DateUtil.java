package com.regis.parking_service.util;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Slf4j
@Component
public class DateUtil {

    @PostConstruct
    public void dateTimeDifference() {
        // No Instant o timezone já é UTC por padrão
        Instant instant = Instant.now();
        log.info("instant: {}", instant); // ex: 2024-10-22T23:44:31.902457500Z

        // No LocalDateTime tem que informar que sua máquina está com o timezone de Sao Paulo para ele converter para UTC
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instantFromLocalDateTime = localDateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
        log.info("instantFromLocalDateTime: {}", instantFromLocalDateTime); // ex: 2024-10-22T23:44:31.902457500Z

        // No OffsetDateTime o timezone já é UTC por padrão
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        log.info("offsetDateTime: {}", offsetDateTime); // ex: 2024-10-22T20:45:49.148493900-03:00
        Instant instantFromOffsetDateTime = offsetDateTime.toInstant();
        log.info("instantFromOffsetDateTime: {}", instantFromOffsetDateTime); // ex: 2024-10-22T23:44:31.902457500Z

        // O ideal é criar o campo data da sua entidade como OffsetDateTime pois ele jpa vai salar no mysql com o timezone UTC.
        // Quando salvar no banco, deve conferir se salvou o campo como UTC
        // Para verificar se o seu mysql está com a visualização em UTC, basta rodar o comando SELEC NOW(). Se o resultado no horario UTC está correto.
    }
}
