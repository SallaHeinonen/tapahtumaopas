package com.example.application.views.etusivu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import com.example.application.data.Event;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Filters implements Specification<Event>{

    private TextField eventName;
    private DatePicker eventDate;
    private TimePicker eventStart;
    private TimePicker eventEnd;
    private TextField eventPlace;
    private TextField eventCity;
    private Checkbox eventFree;

    public TextField getEventName() {
        return eventName;
    }

    public void setEventName(TextField eventName) {
        this.eventName = eventName;
    }

    public DatePicker getEventDate() {
        return eventDate;
    }

    public void setEventDate(DatePicker eventDate) {
        this.eventDate = eventDate;
    }

    public TimePicker getEventStart() {
        return eventStart;
    }

    public void setEventStart(TimePicker eventStart) {
        this.eventStart = eventStart;
    }

    public TimePicker getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(TimePicker eventEnd) {
        this.eventEnd = eventEnd;
    }

    public TextField getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(TextField eventPlace) {
        this.eventPlace = eventPlace;
    }

    public TextField getEventCity() {
        return eventCity;
    }

    public void setEventCity(TextField eventCity) {
        this.eventCity = eventCity;
    }

    public Checkbox getEventFree() {
        return eventFree;
    }

    public void setEventFree(Checkbox eventFree) {
        this.eventFree = eventFree;
    }

    @Override
    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        if (!eventName.isEmpty()) {
            Predicate eventNamePredicate = criteriaBuilder
                .like(criteriaBuilder.lower(root.get("name")), eventName.getValue().toLowerCase() + "%");
            predicateList.add(eventNamePredicate);
        }
        if (!eventDate.isEmpty()) {
            Predicate eventDatePredicate = criteriaBuilder
                .equal(root.get("date"), 
                eventDate.getValue());
            predicateList.add(eventDatePredicate);
        }
        if (!eventStart.isEmpty()) {
            Predicate eventStartPredicate = criteriaBuilder.greaterThanOrEqualTo(
                    root.get("startTime"), 
                    eventStart.getValue());
            predicateList.add(eventStartPredicate);
        }
        if (!eventEnd.isEmpty()) {
            Predicate eventEndPredicate = criteriaBuilder.lessThanOrEqualTo
                (root.get("endTime"), eventEnd.getValue());
            predicateList.add(eventEndPredicate);
        }
        if (!eventPlace.isEmpty()) {
            Predicate eventPlacePredicate = criteriaBuilder
                .like(criteriaBuilder.lower(root.get
                ("eventLocation").get("place")),
                "%" + eventPlace.getValue().toLowerCase() + "%");
            predicateList.add(eventPlacePredicate);
        }
        if (!eventCity.isEmpty()) {
            Predicate eventCityPredicate = criteriaBuilder
                .like(criteriaBuilder.lower(root.get
                ("eventLocation").get("city")), 
                "%" + eventCity.getValue().toLowerCase() + "%");
            predicateList.add(eventCityPredicate);
        }
        if (eventFree != null) {
            Predicate eventFreePredicate = criteriaBuilder
                .equal(root.get("free"), true);
            predicateList.add(eventFreePredicate);
        }
        // if (predicateList.isEmpty()) {
        //     return criteriaBuilder.conjunction();
        // }

        //return criteriaBuilder.and(predicateList.toArray(Predicate[]::new));
       return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }
    


}
