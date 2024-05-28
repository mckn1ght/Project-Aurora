package com.example.cloudComputing.repository;

import com.example.cloudComputing.dto.CommentDto;
import com.example.cloudComputing.entity.Comment;
import com.example.cloudComputing.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  CommentDto save(CommentDto commentDto);

  List<Comment> findAll();

  Comment getById(Long id);

  List<Comment> findByJobId(Long commentId);
}



