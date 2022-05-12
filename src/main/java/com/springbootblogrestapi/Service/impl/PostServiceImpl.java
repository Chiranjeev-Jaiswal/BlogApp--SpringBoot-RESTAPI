package com.springbootblogrestapi.Service.impl;

import com.springbootblogrestapi.Entity.Post;
import com.springbootblogrestapi.Exception.ResourceNotFoundException;
import com.springbootblogrestapi.Repository.PostRepository;
import com.springbootblogrestapi.Service.PostService;
import com.springbootblogrestapi.payload.PostDto;
import com.springbootblogrestapi.payload.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper=mapper;

    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //Convert DTO to entity

        Post post=mapToEntity(postDto);
        Post newPost=postRepository.save(post);

        //Convert entity to DTO
        PostDto postResponse=mapToDto(newPost);
        return postResponse;

    }

    @Override
    public PostResponse getAllPost(int pageNo,int pageSize,String sortBy,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        //create pageable instance
        Pageable pageable=PageRequest.of(pageNo,pageSize, sort);

        Page<Post> posts=postRepository.findAll(pageable);


        //get Content from page object

        List<Post> listOfPosts=posts.getContent();

        List<PostDto>content=listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
        }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));


        return mapToDto(post);
    }

    @Override
    public PostDto UpdatePost(PostDto postDto, long id) {
        //get post by id from the database

        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost=postRepository.save(post);

        return mapToDto(updatePost);
    }

    //get post by ID from the database
    @Override
    public void DeletePostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

    //convert entity into Dto

    private PostDto mapToDto(Post post)
    {

        PostDto postDto=mapper.map(post,PostDto.class);

//        PostDto postDto=new PostDto();

//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());

        return postDto;
    }

    //convert DTO to entity

    private Post mapToEntity(PostDto postDto)
    {
        Post post=mapper.map(postDto,Post.class);
//        Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
}
