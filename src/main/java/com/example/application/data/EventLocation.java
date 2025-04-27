package com.example.application.data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class EventLocation extends AbstractEntity {
    @NotNull
    private String place;
    @NotNull
    private String address;
    @NotNull
    private String city;
    //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
    // Relations
    @OneToMany(mappedBy = "eventLocation")
    private List<Event> eventList;
    public List<Event> getEventList() {
        return eventList;
    }
    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
    // Getters & setters
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }




}
