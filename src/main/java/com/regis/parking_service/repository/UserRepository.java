package com.regis.parking_service.repository;

import com.regis.parking_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  @Query(value = "select u from User u where email = :email")
  User findByEmailWithJpql(String email);

  @Query(value = "select * from users where email = ?1", nativeQuery = true)
  User findByEmailWithNativeQuery(String email);
}
