package com.springbootblogrestapi.Controller;


import com.springbootblogrestapi.Entity.Comment;
import com.springbootblogrestapi.Service.CommentService;
import com.springbootblogrestapi.payload.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")

public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@PathVariable(value = "postId") long postId,
                                                  @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);


    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto>getCommentsByPostId(@PathVariable(value="postId")long postId)
    {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{Id}")
    public ResponseEntity<CommentDto>getCommentById(@PathVariable(value ="postId") long postId,
                                                    @PathVariable(value="Id")long commentId)
    {
        CommentDto commentDto=commentService.getCommentById(postId,commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);

    }

    @PutMapping("/posts/{postId}/comments/{Id}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable(value="postId") long postId,
                                                   @PathVariable(value="Id")long commentId,
                                                   @Valid @RequestBody  CommentDto commentDto)
    {
        CommentDto updateComment=commentService.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(updateComment,HttpStatus.OK);

    }

    @DeleteMapping("/posts/{postId}/comments/{Id}")
    public ResponseEntity<String>deleteComment(@PathVariable(value="postId") long postId,
                                               @PathVariable(value="Id")long commentId)
    {
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment deleted sucessfully",HttpStatus.OK);

    }
}
