package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.CommentDto;
import com.example.cloudComputing.entity.Comment;
import com.example.cloudComputing.entity.Job;
import com.example.cloudComputing.entity.User;
import com.example.cloudComputing.mapper.CommentMapper;
import com.example.cloudComputing.repository.ExpertiseAreaRepository;
import com.example.cloudComputing.repository.CommentRepository;
import com.example.cloudComputing.repository.JobRepository;
import com.example.cloudComputing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.cloudComputing.constants.Constants.*;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private ExpertiseAreaRepository expertiseAreaRepository;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private UserRepository userRepository;

  public Comment addComment(Long jobId, Long userId, String content) {
    // Fetch existing Job and User entities
    Job job = jobRepository.findById(jobId).orElseThrow(() -> new IllegalArgumentException("Invalid job ID"));
    User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

    // Create and populate the Comment entity
    Comment comment = new Comment();
    comment.setJob(job);
    comment.setUser(user);
    comment.setContent(content);
    comment.setTimestamp(LocalDateTime.now());

    // Save and return the Comment entity
    return commentRepository.save(comment);
  }
  public List<Comment> getCommentsByJobId(Long jobId) {
    return commentRepository.findByJobId(jobId);
  }
}

