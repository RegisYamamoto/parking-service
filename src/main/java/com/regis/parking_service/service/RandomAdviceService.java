package com.regis.parking_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.regis.parking_service.controller.dto.RandomAdviceIncomingResponse;
import com.regis.parking_service.controller.dto.RandomAdviceOutgoingResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class RandomAdviceService {

  public RandomAdviceIncomingResponse getRandomAdvice() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create("https://api.adviceslip.com/advice"))
      .build();

    HttpClient httpClient = HttpClient.newBuilder().build();

    HttpResponse<String> outgoingResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    String body = outgoingResponse.body();

//    RandomAdviceIncomingResponse response = parseOutgoingResponseBodyToIncomingResponse(body);
    RandomAdviceIncomingResponse response = parseOutgoingResponseBodyToIncomingResponseUsingJsonNode(body);

    return response;
  }

  // Usando o readValue precisa criar um objeto para receber os valoes do Json
  private RandomAdviceIncomingResponse parseOutgoingResponseBodyToIncomingResponse(String body) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    RandomAdviceOutgoingResponse randomAdviceOutgoingResponse = objectMapper.readValue(body, RandomAdviceOutgoingResponse.class);

    String id = randomAdviceOutgoingResponse.getSlip().getId();
    String advice = randomAdviceOutgoingResponse.getSlip().getAdvice();

    RandomAdviceIncomingResponse randomAdviceIncomingResponse = new RandomAdviceIncomingResponse();
    randomAdviceIncomingResponse.setId(id);
    randomAdviceIncomingResponse.setAdvice(advice);

    return randomAdviceIncomingResponse;
  }

  // Usando o readTree não precisa criar um objeto para receber os valoes do Json
  private RandomAdviceIncomingResponse parseOutgoingResponseBodyToIncomingResponseUsingJsonNode(String body) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = objectMapper.readTree(body);

    String id = rootNode.path("slip").path("id").asText();
    String advice = rootNode.path("slip").path("advice").asText();

    RandomAdviceIncomingResponse randomAdviceIncomingResponse = new RandomAdviceIncomingResponse();
    randomAdviceIncomingResponse.setId(id);
    randomAdviceIncomingResponse.setAdvice(advice);

    return randomAdviceIncomingResponse;
  }
}
