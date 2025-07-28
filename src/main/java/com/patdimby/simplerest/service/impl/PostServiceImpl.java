package com.patdimby.simplerest.service.impl;

import com.patdimby.simplerest.dto.PostDto;
import com.patdimby.simplerest.model.Post;
import com.patdimby.simplerest.mapper.PostMapper;
import com.patdimby.simplerest.repository.PostRepository;
import com.patdimby.simplerest.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }
}
