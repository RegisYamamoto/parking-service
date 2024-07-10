package com.regis.parking_service.controller.dto;

import com.regis.parking_service.entity.User;
import com.regis.parking_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity createUser(@RequestBody @Valid UserDto userDto) {
    User user = userService.createUser(userDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }
}
