package com.example.application.views.etusivu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import com.example.application.data.Event;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Filters extends VerticalLayout implements Specification<Event> {

    private TextField eventName = new TextField("Tapahtuman nimi");
    private DatePicker eventDate = new DatePicker("Tapahtumapäivä");
    private TimePicker eventStart = new TimePicker("Aloitusaika");
    private TimePicker eventEnd = new TimePicker("Päättymisaika");
    private TextField eventPlace = new TextField("Tapahtumapaikka");
    private TextField eventCity = new TextField("Kaupunki/Kaupunginosa");
    private Checkbox eventFree = new Checkbox("Maksuton");
    private Button searchBtn = new Button("Etsi");
    private Button clearBtn = new Button("Tyhjennä");
    
    public Filters(Runnable onSearch) {

        clearBtn.addClickListener(e -> {
            eventName.clear();
            eventDate.clear();
            eventStart.clear();
            eventEnd.clear();
            eventPlace.clear();
            eventCity.clear();
            eventFree.clear();
            onSearch.run();
        });

        searchBtn.addClickListener(e -> {
            onSearch.run();
        });

        setPadding(false);
        setMargin(false);
        searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        clearBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
        add(eventName, eventDate, eventStart, eventEnd, eventPlace, eventCity, eventFree, searchBtn, clearBtn);
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
        if (eventFree.getValue() != null && eventFree.getValue()) {
            Predicate eventFreePredicate = criteriaBuilder.equal(root.get("free"), true);
            predicateList.add(eventFreePredicate);
        }

        return criteriaBuilder.and(predicateList.toArray(Predicate[]::new));
    }
}
// return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));