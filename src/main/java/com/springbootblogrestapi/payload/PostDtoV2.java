package com.springbootblogrestapi.payload;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
public class PostDtoV2 {

    private long id;

    //title should not be null or empty
    @NotEmpty
    //title should have atleast 2 characters
    @Size(min=2,message="Post title should have at least 2 characters")
    private String  title;

    //post Description should not be null or empty
    @NotEmpty
    //post Description should have atleast 8 characters
    @Size(min=8,message="Post description should have at least 2 characters")
    private String description;


    //post content should not be null or empty
    @NotEmpty
    private String content;


    private Set<CommentDto> comments;

    private List<String> tags;
}
