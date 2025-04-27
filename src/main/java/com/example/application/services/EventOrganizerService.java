package com.example.application.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.application.data.EventOrganizer;
import com.example.application.data.EventOrganizerRepository;

@Service
public class EventOrganizerService {

    private final EventOrganizerRepository repository;

    public EventOrganizerService(EventOrganizerRepository repository) {
        this.repository = repository;

    }
    public List<EventOrganizer> getOrganizers() {
        return repository.findAll();
    }



}
