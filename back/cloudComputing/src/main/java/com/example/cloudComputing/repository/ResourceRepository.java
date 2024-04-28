package com.example.cloudComputing.repository;

import com.example.cloudComputing.dto.ResourceDto;
import com.example.cloudComputing.dto.UserDto;
import com.example.cloudComputing.entity.Resource;
import com.example.cloudComputing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
  ResourceDto save(ResourceDto resourceto);

  List<Resource> findAll();

  Resource getById(Long id);

  List<Resource> findAllByUserId(int userId);

  Resource findByUserIdAndAndType(int userId, String type);

}
