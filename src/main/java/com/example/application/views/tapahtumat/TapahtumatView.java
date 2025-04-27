package com.example.application.views.tapahtumat;

import com.example.application.data.Event;
import com.example.application.services.EventInfoService;
import com.example.application.services.EventLocationService;
import com.example.application.services.EventOrganizerService;
import com.example.application.services.EventService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import jakarta.annotation.security.RolesAllowed;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Tapahtumat")
@Route("tapahtumat")
@Menu(order = 1, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@RolesAllowed("USER")
public class TapahtumatView extends Composite<VerticalLayout> {
    private final Grid<Event> eventGrid = new Grid<>(Event.class, false);
    private final TextField eventNameField = new TextField("Tapahtuman nimi");
    private final DatePicker eventDatePicker = new DatePicker("Tapahtumapäivä");
    private final TimePicker eventStartTime = new TimePicker("Aloitusaika");
    private final TimePicker eventEndTime = new TimePicker("Päättymisaika");
    private final TextField eventPlaceField = new TextField("Tapahtumapaikka");
    private final TextField eventCityField = new TextField("Kaupunki/Kaupunginosa");
    private final Checkbox eventFreeCheckBox = new Checkbox("Maksuton");

    private final EventService eventService;
    private final EventLocationService eventLocationService;
    private final EventOrganizerService eventOrganizerService;
    private final EventInfoService eventInfoService;

    public TapahtumatView(EventService eventService,
                    EventLocationService eventLocationService,
                    EventOrganizerService eventOrganizerService,
                    EventInfoService eventInfoService) {
        this.eventService = eventService;
        this.eventLocationService = eventLocationService;
        this.eventOrganizerService = eventOrganizerService;
        this.eventInfoService = eventInfoService;

        getContent().setHeight("100%");
        getContent().getStyle().set("background-color", "#b3e6b3"); // Green

        // Header-osio
        HorizontalLayout headerHorizontal = new HorizontalLayout();
        headerHorizontal.addClassName(Gap.MEDIUM);
        headerHorizontal.setWidth("100%");
        headerHorizontal.setHeight("min-content");
        headerHorizontal.getStyle().set("background-color","#b3b3e6"); // Lavender

        // Main layout for grid and inputs
        HorizontalLayout mainLayoutHorizontal = new HorizontalLayout();
        mainLayoutHorizontal.addClassName(Gap.MEDIUM);
        mainLayoutHorizontal.setWidth("100%");
        mainLayoutHorizontal.setHeight("100%");
        mainLayoutHorizontal.getStyle().set("background-color", "#cce7ff"); // Blue

        // Grid for data on the left
        VerticalLayout eventsGridLayout = new VerticalLayout();
        eventsGridLayout.setWidth(null);
        eventsGridLayout.setHeightFull();
        eventsGridLayout.getStyle().set("background-color", "#ffb3d9"); // Lavender blush

        // Input fields for data on right
        VerticalLayout textFieldsLayout = new VerticalLayout();
        textFieldsLayout.setWidth(null);
        textFieldsLayout.setHeightFull();
        textFieldsLayout.addClassName(Gap.SMALL);
        textFieldsLayout.getStyle().set("background-color", "#ffe066"); // Yellow
    
        getContent().add(headerHorizontal, mainLayoutHorizontal);
        mainLayoutHorizontal.add(eventsGridLayout, textFieldsLayout);
        mainLayoutHorizontal.setFlexGrow(5, eventsGridLayout);
        mainLayoutHorizontal.setFlexGrow(0, textFieldsLayout);
    }
}
