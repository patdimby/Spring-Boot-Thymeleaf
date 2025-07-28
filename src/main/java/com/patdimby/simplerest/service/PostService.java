package com.patdimby.simplerest.service;

import com.patdimby.simplerest.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);
}
