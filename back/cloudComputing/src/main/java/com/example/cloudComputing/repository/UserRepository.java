package com.example.cloudComputing.repository;

import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  UserDto save(UserDto userDto);

  List<User> findAll();

  User getById(Long id);

  User getByUsername(String username);

  List<User> getAllByPermission(int permission);

  List<User> getAllByCreditAfter(double credit);

  List<User> getAllByCreditBefore(double credit);

  Boolean existsUserByUsername(String username);
  Boolean existsUserByEmail(String email);
}
