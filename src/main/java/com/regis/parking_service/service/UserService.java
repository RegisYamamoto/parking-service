package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.UserRequestDto;
import com.regis.parking_service.controller.dto.UserResponseDto;
import com.regis.parking_service.entity.User;
import com.regis.parking_service.entity.UserRole;
import com.regis.parking_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User createUser(UserRequestDto userDto) {
    User existingUser = userRepository.findByEmail(userDto.getEmail());

    if (existingUser != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuario com o email: " + userDto.getEmail());
    }

    String hashedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());

    User newUser = User.builder()
        .uuid(UUID.randomUUID())
        .email(userDto.getEmail())
        .password(hashedPassword)
        .role(UserRole.getIdByRole(userDto.getRole()))
        .build();

    return userRepository.save(newUser);
  }

  public UserResponseDto getByEmail(String email) {
    User user = userRepository.findByEmailWithJpql(email);
    UserResponseDto userResponseDto = UserResponseDto.builder()
            .id(user.getId())
            .uuid(user.getUuid())
            .email(user.getEmail())
            .password(user.getPassword())
            .role(UserRole.getRoleById(user.getRole()))
            .build();
    return userResponseDto;
  }

  public Page<UserResponseDto> getAllUsers(Pageable pageable) {
    Page<User> users = userRepository.findAll(pageable);
    Page<UserResponseDto> userResponseDtos = convertToDto(users);
    return userResponseDtos;
  }

  public Page<UserResponseDto> convertToDto(Page<User> users) {
    List<UserResponseDto> userDTOs = users.getContent().stream()
            .map(user -> {
              UserResponseDto dto = UserResponseDto.builder()
                      .id(user.getId())
                      .uuid(user.getUuid())
                      .email(user.getEmail())
                      .role(UserRole.getRoleById(user.getRole()))
                      .build();
              return dto;
            })
            .collect(Collectors.toList());

    return new PageImpl<>(userDTOs, users.getPageable(), users.getTotalElements());
  }
}
