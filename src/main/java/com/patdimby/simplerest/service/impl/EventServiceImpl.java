package com.patdimby.simplerest.service.impl;

import java.util.List;

import com.patdimby.simplerest.model.Event;
import com.patdimby.simplerest.repository.EventRepository;
import com.patdimby.simplerest.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }    
}