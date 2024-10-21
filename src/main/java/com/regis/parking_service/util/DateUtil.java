package com.regis.parking_service.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Component
public class DateUtil {

    @PostConstruct
    public void DateTimeDifference() {
        // No Instant o timezone já é UTC por padrão
        Instant instant = Instant.now();
        System.out.println(instant);

        // No LocalDateTime tem que informar que sua máquina está com o timezone de Sao Paulo para ele converter para UTC
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instantFromLocalDateTime = localDateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
        System.out.println(instantFromLocalDateTime);

        // No OffsetDateTime o timezone já é UTC por padrão
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        Instant instantFromOffsetDateTime = offsetDateTime.toInstant();
        System.out.println(instantFromOffsetDateTime);

        // O ideal é criar o campo data da sua entidade como OffsetDateTime pois ele jpa vai salar no mysql com o timezone UTC.
        // Quando salvar no banco, deve conferir se salvou o campo como UTC
        // Para verificar se o seu mysql está com a visualização em UTC, basta rodar o comando SELEC NOW(). Se o resultado no horario UTC está correto.
    }
}
