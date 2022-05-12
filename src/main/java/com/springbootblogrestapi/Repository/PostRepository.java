package com.springbootblogrestapi.Repository;

import com.springbootblogrestapi.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>{

}
