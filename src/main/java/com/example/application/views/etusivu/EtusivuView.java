package com.example.application.views.etusivu;

import com.example.application.data.Event;
import com.example.application.data.EventLocation;
import com.example.application.data.SamplePerson;
import com.example.application.services.EventInfoService;
import com.example.application.services.EventLocationService;
import com.example.application.services.EventOrganizerService;
import com.example.application.services.EventService;
import com.example.application.services.SamplePersonService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.List;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Etusivu")
@Route(value = "", layout = MainLayout.class)
@Menu(order = 0, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
@AnonymousAllowed
// beforeEnterObserver
public class EtusivuView extends Composite<VerticalLayout> {

    private final Grid<Event> eventGrid = new Grid<>(Event.class, false);
    private final BeanValidationBinder<Event> binder;

    private Event event;
    private EventService eventService;
    private EventLocationService eventLocationService;
    private EventOrganizerService eventOrganizerService;
    private EventInfoService eventInfoService;
    private Filters filters;

    public EtusivuView(EventService eventService,
                    EventLocationService eventLocationService,
                    EventOrganizerService eventOrganizerService,
                    EventInfoService eventInfoService) {
        this.eventService = eventService;
        this.eventLocationService = eventLocationService;
        this.eventOrganizerService = eventOrganizerService;
        this.eventInfoService = eventInfoService;

        HorizontalLayout mainLayoutHorizontal = new HorizontalLayout();
        mainLayoutHorizontal.setWidth("100%");

        // Left layout for inputs
        VerticalLayout textFieldsLayout = new VerticalLayout();
        textFieldsLayout.setWidth(null);
        
        // Right layout for data grid
        VerticalLayout eventsGridLayout = new VerticalLayout();
        eventsGridLayout.setWidth(null);

        // Configure event grid
        eventGrid.setItems();
        eventGrid.removeAllColumns();
        // First remove all columns to create the necessary ones for viewing
        eventGrid.addColumn("name").setAutoWidth(true).setHeader("Tapahtuman nimi");
        eventGrid.addColumn("date").setAutoWidth(true).setHeader("Tapahtumapäivä");
        eventGrid.addColumn("startTime").setAutoWidth(true).setHeader("Aloitusaika");
        eventGrid.addColumn("endTime").setAutoWidth(true).setHeader("Päättymisaika");
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

        // Adding components+setting flex
        getContent().add(mainLayoutHorizontal);
        mainLayoutHorizontal.add(textFieldsLayout, eventsGridLayout);
        addFilterInputs(textFieldsLayout); // Call the method to add the fields into the layout
        mainLayoutHorizontal.setFlexGrow(0, textFieldsLayout);
        mainLayoutHorizontal.setFlexGrow(5, eventsGridLayout);

        eventsGridLayout.add(eventGrid);
    }

    private void addFilterInputs(VerticalLayout verticalLayout) {
        filters = new Filters(this::refreshGrid);
        verticalLayout.add(filters);
    }

    private void refreshGrid() {
        eventGrid.setItems(eventService.searchEvents(filters));
    }

    private void setGridData(Grid<Event> grid) {
        grid.setItems(eventService.getAll());
    }
}
