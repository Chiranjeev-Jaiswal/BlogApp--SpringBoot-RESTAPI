package com.springbootblogrestapi.Service;

import com.springbootblogrestapi.Entity.Comment;
import com.springbootblogrestapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto>getCommentsByPostId(long postId);
    CommentDto getCommentById(long postId,long commentId);

    CommentDto updateComment(long postId,long commentId,CommentDto commenRequest);

    void deleteComment(long postId,long commentId);



}
