package com.example.application.data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
// import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class EventOrganizer extends AbstractEntity {
    private String firstName;
    private String lastName;
    private String organization;
    @NotNull
    private String phone;
    private String email;


    //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
    // Relations
    @OneToMany(mappedBy = "eventOrganizer")
    private List<Event> eventList;
    public List<Event> getEventList() {
        return eventList;
    }
    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
    //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
    // Getters & setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
