package com.example.application.services;

import org.springframework.stereotype.Service;

import com.example.application.data.EventInfoRepository;

@Service
public class EventInfoService {

    private final EventInfoRepository repository;

    public EventInfoService(EventInfoRepository repository) {
        this.repository = repository;
    }

}
