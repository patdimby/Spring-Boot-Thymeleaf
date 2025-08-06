package com.patdimby.simplerest.service.impl;

import com.patdimby.simplerest.model.Resume;
import com.patdimby.simplerest.repository.BlogRepository;
import com.patdimby.simplerest.repository.ResumeRepository;
import com.patdimby.simplerest.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }
}
