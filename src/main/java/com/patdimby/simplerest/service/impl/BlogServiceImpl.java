package com.patdimby.simplerest.service.impl;

import java.util.List;

import com.patdimby.simplerest.model.Blog;
import com.patdimby.simplerest.repository.BlogRepository;
import com.patdimby.simplerest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }    
}