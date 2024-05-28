package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.JobDto;
import com.example.cloudComputing.entity.Comment;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {
//  JobDto add(JobDto jobDto);

  Comment addComment(Long jobId, Long userId, String content);

  List<Comment> getCommentsByJobId(Long jobId);




}
