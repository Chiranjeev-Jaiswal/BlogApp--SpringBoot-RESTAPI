package com.springbootblogrestapi.Controller;


import com.springbootblogrestapi.Entity.Post;
import com.springbootblogrestapi.Service.PostService;
import com.springbootblogrestapi.Utils.AppConstants;
import com.springbootblogrestapi.payload.PostDto;
import com.springbootblogrestapi.payload.PostDtoV2;
import com.springbootblogrestapi.payload.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    //create blogPost
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto>createPost(@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }

    //get all post rest api
    @GetMapping("/api/v1/posts")
    public PostResponse getALlPosts(@RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
                                    @RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false)int pageSize,
                                    @RequestParam(value="sort",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
                                    @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir
    )
    {
        return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
    }

    //get post by Id
    @GetMapping(value="/api/posts/{id}",params="v=1")
    public  ResponseEntity<PostDto>getPostById(@PathVariable(name = "id") long id)
    {
        return ResponseEntity.ok(postService.getPostById(id));
    }


    //get post by Id
    @GetMapping(value="/api/posts/{id}",params ="v=2")
    public  ResponseEntity<PostDtoV2>getPostByIdV2(@PathVariable(name = "id") long id)
    {
        PostDto postDto=postService.getPostById(id);
        PostDtoV2 postDtoV2=new PostDtoV2();
        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setContent(postDto.getContent());

        List<String>tags=new ArrayList<>();
        tags.add("Java");
        tags.add("Spring");
        tags.add("AWS");

        postDtoV2.setTags(tags);

        return ResponseEntity.ok(postDtoV2);
    }

    //update post by id rest api
    @PutMapping("/api/v1/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto>updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name="id") long id)
    {
        PostDto postResponse=postService.UpdatePost(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //Delete the post rest api
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String>deletePost(@Valid @PathVariable(name="id") long id)
    {
        postService.DeletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.",HttpStatus.OK);
    }















}
