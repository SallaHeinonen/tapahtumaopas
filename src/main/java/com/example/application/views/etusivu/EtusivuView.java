package com.example.application.views.etusivu;

import com.example.application.data.Event;
import com.example.application.data.EventLocation;
import com.example.application.data.SamplePerson;
import com.example.application.services.EventInfoService;
import com.example.application.services.EventLocationService;
import com.example.application.services.EventOrganizerService;
import com.example.application.services.EventService;
import com.example.application.services.SamplePersonService;
import com.example.application.views.FooterLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Etusivu")
@Route(value = "etusivu", layout = FooterLayout.class)
@Menu(order = 0, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
@AnonymousAllowed
// beforeEnterObserver
public class EtusivuView extends Composite<VerticalLayout> {

    private final Grid<Event> eventGrid = new Grid<>(Event.class, false);
    private Event event;
    private final BeanValidationBinder<Event> binder;
    
    private final TextField eventNameField = new TextField("Tapahtuman nimi");
    private final DatePicker eventDatePicker = new DatePicker("Tapahtumapäivä");
    private final TimePicker eventStartTime = new TimePicker("Aloitusaika");
    private final TimePicker eventEndTime = new TimePicker("Päättymisaika");
    private final TextField eventPlaceField = new TextField("Tapahtumapaikka");
    private final TextField eventCityField = new TextField("Kaupunki/Kaupunginosa");
    private final Checkbox eventFreeCheckBox = new Checkbox("Maksuton");

    private final Button searchBtn = new Button("Etsi");
    private final Button clearBtn = new Button("Tyhjennä");
    
    private final EventService eventService;
    private final EventLocationService eventLocationService;
    private final EventOrganizerService eventOrganizerService;
    private final EventInfoService eventInfoService;

    public EtusivuView(EventService eventService,
                    EventLocationService eventLocationService,
                    EventOrganizerService eventOrganizerService,
                    EventInfoService eventInfoService) {
        this.eventService = eventService;
        this.eventLocationService = eventLocationService;
        this.eventOrganizerService = eventOrganizerService;
        this.eventInfoService = eventInfoService;
                
        // getContent().setWidth("100%");
        //getContent().setHeight("100%");

        HorizontalLayout mainLayoutHorizontal = new HorizontalLayout();
        // mainLayoutHorizontal.addClassName(Gap.MEDIUM);
        mainLayoutHorizontal.setWidth("100%");
        // mainLayoutHorizontal.setHeight("100%");

        // Left layout for inputs
        VerticalLayout textFieldsLayout = new VerticalLayout();
        textFieldsLayout.setWidth(null);
        // textFieldsLayout.setHeightFull();
        textFieldsLayout.addClassName(Gap.SMALL);
        
        // Right layout for data grid
        VerticalLayout eventsGridLayout = new VerticalLayout();
        eventsGridLayout.setWidth(null);
        // eventsGridLayout.setHeightFull();

        // Configure event grid
        eventGrid.setItems();
        eventGrid.removeAllColumns();
        // First remove all columns to create the necessary ones for viewing
        eventGrid.addColumn("name").setAutoWidth(true).setHeader("Tapahtuman nimi");
        eventGrid.addColumn("date").setAutoWidth(true).setHeader("Tapahtumapäivä");
        eventGrid.addColumn("startTime").setAutoWidth(true).setHeader("Aloitusaika");
        eventGrid.addColumn("endTime").setAutoWidth(true).setHeader("Päättymisaika");
        // TODO: katso jos lisää sarakkeita
        eventGrid.addColumn(event -> event.getEventLocation() != null ? event.getEventLocation().getPlace() : "").setAutoWidth(true).setHeader("Tapahtumapaikka");
        eventGrid.addColumn(event -> event.getEventLocation() != null ? event.getEventLocation().getCity() : "").setAutoWidth(true).setHeader("Kaupunki/kaupunginosa");
        LitRenderer<Event> free = LitRenderer.<Event>of(
            "<vaadin-icon icon='vaadin:${item.icon}' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: ${item.color};'></vaadin-icon>")
            .withProperty("icon", important -> important.isFree() ? "check" : "minus").withProperty("color",
                    important -> important.isFree()
                            ? "var(--lumo-primary-text-color)"
                            : "var(--lumo-disabled-text-color)");
        eventGrid.addColumn(free).setAutoWidth(true).setHeader("Maksuton");

        eventGrid.setItems(query -> eventService.list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
        // eventGrid.setItems(eventService.getAll());
        eventGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        setGridData(eventGrid);


        binder = new BeanValidationBinder<>(Event.class);
        
        // Footer layout with login
        // HorizontalLayout loginHorizontal = new HorizontalLayout();
        // loginHorizontal.addClassName(Gap.MEDIUM);
        // loginHorizontal.setWidth("100%");
        // loginHorizontal.setHeight("min-content");
        
        // Styling for buttons
        searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        clearBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        // Adding components+setting flex
        getContent().add(mainLayoutHorizontal); // , loginHorizontal
        mainLayoutHorizontal.add(textFieldsLayout, eventsGridLayout);
        mainLayoutHorizontal.setFlexGrow(0, textFieldsLayout);
        mainLayoutHorizontal.setFlexGrow(5, eventsGridLayout);

        textFieldsLayout.add(eventNameField, eventDatePicker, eventStartTime, eventEndTime, 
                        eventPlaceField, eventCityField, eventFreeCheckBox, searchBtn, clearBtn);

        eventsGridLayout.add(eventGrid);
        // loginHorizontal.add(loginBtn, registerBtn);
    }

    private void setGridData(Grid<Event> grid) {
        grid.setItems(eventService.getAll());
    }

}
