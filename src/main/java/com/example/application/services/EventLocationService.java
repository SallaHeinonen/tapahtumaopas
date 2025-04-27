package com.example.application.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.application.data.EventLocation;
import com.example.application.data.EventLocationRepository;

@Service
public class EventLocationService {

    private final EventLocationRepository repository;

    public EventLocationService(EventLocationRepository repository) {
        this.repository = repository;
    }

    public List<EventLocation> getLocations() {
        return repository.findAll();
    }



    
}
