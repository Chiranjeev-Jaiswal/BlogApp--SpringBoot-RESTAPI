package com.springbootblogrestapi.Service;

import com.springbootblogrestapi.Entity.Post;
import com.springbootblogrestapi.payload.PostDto;
import com.springbootblogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(long id);

    PostDto UpdatePost(PostDto postDto, long id);

    void DeletePostById(long id);

}
