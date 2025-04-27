package com.example.application.data;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Event extends AbstractEntity {
    @NotNull
    private String name;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private boolean free;
    //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
    // Relations
    // Tapahtumasijainti
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eventlocation_id")
    // the id is generated in lowercase
    private EventLocation eventLocation;
    public EventLocation getEventLocation() {
        return eventLocation;
    }
    public void setEventLocation(EventLocation eventLocation) {
        this.eventLocation = eventLocation;
    }
    // Tapahtuman järjestäjä
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventorganizer_id")
    private EventOrganizer eventOrganizer;
    public EventOrganizer getEventOrganizer() {
        return eventOrganizer;
    }
    public void setEventOrganizer(EventOrganizer eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }
    // Tapahtuman lisäinfo
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "eventinfo_id")
    @JsonManagedReference
    private EventInfo eventInfo;
    public EventInfo getEventInfo() {
        return eventInfo;
    }
    public void setEventInfo(EventInfo eventInfo) {
        this.eventInfo = eventInfo;
    }
    //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
    // Getters & setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public boolean isFree() {
        return free;
    }
    public void setFree(boolean free) {
        this.free = free;
    }

}
