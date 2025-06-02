package com.musiconnect.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.musiconnect.api.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
  boolean existsByEmail(@Param("email") String email);

  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userProfile.username = :username")
  boolean existsByUsername(@Param("username") String username);

  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email AND u.userProfile.username = :username")
  boolean existsByEmailAndUsername(@Param("email") String email, @Param("username") String username);

  @Query("SELECT u FROM User u WHERE u.userProfile.username = :username")
  Optional<User> findByUsername(@Param("username") String username);

  @Query("SELECT u FROM User u WHERE u.userProfile.type = :type")
  List<User> findByUserType(@Param("type") String userType);

  @Query("SELECT u FROM User u WHERE u.userProfile.birthdate BETWEEN :minDate AND :maxDate")
  List<User> findByAgeBetween(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate);
}