package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.UserDto;
import com.regis.parking_service.entity.User;
import com.regis.parking_service.entity.UserRole;
import com.regis.parking_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User createUser(UserDto userDto) {
    User existingUser = userRepository.findByEmail(userDto.email());

    if (existingUser != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuario com o email: " + userDto.email());
    }

    String hashedPassword = new BCryptPasswordEncoder().encode(userDto.password());

    User newUser = User.builder()
        .uuid(UUID.randomUUID())
        .email(userDto.email())
        .password(hashedPassword)
        .role(UserRole.getIdByRole(userDto.role()))
        .build();

    return userRepository.save(newUser);
  }
}
