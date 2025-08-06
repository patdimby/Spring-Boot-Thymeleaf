package com.patdimby.simplerest.service.impl;

import java.util.List;

import com.patdimby.simplerest.model.Team;
import com.patdimby.simplerest.repository.TeamRepository;
import com.patdimby.simplerest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }    
}