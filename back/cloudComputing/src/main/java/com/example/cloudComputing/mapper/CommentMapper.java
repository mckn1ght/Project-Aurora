package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.CommentDto;
import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.entity.Comment;
import com.example.cloudComputing.entity.Job;
import com.example.cloudComputing.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
  public Comment mapToComment(CommentDto commentDto) {
    return Comment.builder()
      .id(commentDto.getId())
      .job(commentDto.getJob())
      .user(commentDto.getUser())
      .content(commentDto.getContent())
      .timestamp(commentDto.getTimestamp())
      .build();
  }

  public CommentDto mapToCommentDto(Comment comment) {
    return CommentDto.builder()
      .id(comment.getId())
      .job(comment.getJob())
      .user(comment.getUser())
      .content(comment.getContent())
      .timestamp(comment.getTimestamp())
      .build();
  }

}
