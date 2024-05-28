package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.CommentDto;
import com.example.cloudComputing.entity.Comment;
import com.example.cloudComputing.mapper.CommentMapper;
import com.example.cloudComputing.serviceImpl.CommentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comment")
public class CommentController {
  @Autowired
  private CommentServiceImpl commentServiceImpl;

  @Autowired
  CommentMapper commentMapper;

  @PostMapping("/add")
  public ResponseEntity<CommentDto> addComment(@RequestBody Map<String, Object> payload) {
    Long jobId = ((Number) payload.get("jobId")).longValue();
    Long userId = ((Number) payload.get("userId")).longValue();
    String content = (String) payload.get("content");

    Comment comment = commentServiceImpl.addComment(jobId, userId, content);
    CommentDto commentDto = commentMapper.mapToCommentDto(comment);

    return ResponseEntity.ok(commentDto);
  }

  @GetMapping("/job/{jobId}")
  public ResponseEntity<List<CommentDto>> getCommentsByJobId(@PathVariable Long jobId) {
    List<Comment> comments = commentServiceImpl.getCommentsByJobId(jobId);
    List<CommentDto> commentDtos = comments.stream().map(commentMapper::mapToCommentDto).toList();

    return ResponseEntity.ok(commentDtos);
  }


}




