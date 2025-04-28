package com.example.application.views;

import com.example.application.data.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.services.UserService;
import com.example.application.views.etusivu.EtusivuView;
import com.example.application.views.hallintapaneeli.HallintapaneeliView;
import com.example.application.views.login.LoginView;
import com.example.application.views.login.RegisterComponent;
import com.example.application.views.tapahtumat.TapahtumatView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Overflow;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Whitespace;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.lineawesome.LineAwesomeIcon;
/**
 * The main view is a top-level placeholder for other views.
 */
@Layout
@AnonymousAllowed
public class MainLayout extends AppLayout {

    private final Div container = new Div();
    private AuthenticatedUser authenticatedUser;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final LoginView loginView;
    /**
     * A simple navigation item component, based on ListItem element.
     */
     public MainLayout(AuthenticatedUser authenticatedUser,
                        UserService userService,
                        PasswordEncoder passwordEncoder,
                        LoginView loginView) {
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.loginView = loginView;
        addToNavbar(createHeaderContent());
        CreateContainer mainContainer = new CreateContainer();
        setContent(mainContainer);

    }
    public class CreateContainer extends VerticalLayout {
        public CreateContainer () {
            setSizeFull();
            container.setSizeFull();
            add(container);
            Footer footer = createFooter();
            add(footer);
            expand(container);
        }
    }

    public Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassName(Gap.MEDIUM);

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {

            Button logoutBtn = new Button("Kirjaudu ulos");
            logoutBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            logoutBtn.addClickListener(e -> { authenticatedUser.logout(); });
            layout.add(logoutBtn);
        } else {
            Button loginBtn = new Button("Kirjaudu sisään");
            Button registerBtn = new Button("Rekisteröidy");
            loginBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            registerBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
            loginBtn.addClickListener(e -> loginView.setOpened(true));
            RegisterComponent registerComponent = new RegisterComponent(userService, passwordEncoder);
            registerBtn.addClickListener(e -> registerComponent.openRegisterDialog());
            layout.add(loginBtn, registerBtn, registerComponent);
        }
        return layout;
    }

    public void showRouterLayoutContent(HasElement content) {
        container.removeAll();
        container.getElement().appendChild(content.getElement());
    }

    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, Component icon, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontWeight.MEDIUM, FontSize.MEDIUM, Whitespace.NOWRAP);

            if (icon != null) {
                link.add(icon);
            }
            link.add(text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, Width.FULL);

        Div layout = new Div();
        layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);

        H1 appName = new H1("Tapahtumaopas");
        appName.addClassNames(Margin.Vertical.MEDIUM, Margin.End.AUTO, FontSize.LARGE);
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames(Display.FLEX, Overflow.AUTO, Padding.Horizontal.MEDIUM, Padding.Vertical.XSMALL);

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames(Display.FLEX, Gap.SMALL, ListStyleType.NONE, Margin.NONE, Padding.NONE);
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);
        }

        header.add(layout, nav);
        return header;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{
            new MenuItemInfo("Etusivu", LineAwesomeIcon.SEARCH_SOLID.create(), EtusivuView.class),
            new MenuItemInfo("Tapahtumat", LineAwesomeIcon.CALENDAR_SOLID.create(), TapahtumatView.class),
            new MenuItemInfo("Hallintapaneeli", LineAwesomeIcon.WRENCH_SOLID.create(), HallintapaneeliView.class),
        };
    }

}
