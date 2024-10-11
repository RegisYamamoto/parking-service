package com.regis.parking_service.controller;

import com.regis.parking_service.controller.dto.UserRequestDto;
import com.regis.parking_service.controller.dto.UserResponseDto;
import com.regis.parking_service.entity.User;
import com.regis.parking_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity createUser(@RequestBody @Valid UserRequestDto userDto) {
    User user = userService.createUser(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @GetMapping("/{email}")
  public ResponseEntity getByEmail(@PathVariable String email) {
    UserResponseDto user = userService.getByEmail(email);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @GetMapping
  public ResponseEntity<Page<UserResponseDto>> getAllUsers(
      @PageableDefault(page = 0, size = 10, sort = "email") Pageable pageable) {
    Page<UserResponseDto> users = userService.getAllUsers(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }
}
