package com.springbootblogrestapi.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private long Id;

    @NotEmpty(message = "name should not be null or empty")

    private String name;


    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    @NotEmpty(message = "Comment should not be null or empty")
    @Size(min = 8,message ="Comment body should be mini. 8 character" )
    private String body;
}
