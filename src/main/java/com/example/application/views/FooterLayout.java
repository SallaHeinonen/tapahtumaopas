package com.example.application.views;

import com.example.application.data.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.services.UserService;
import com.example.application.views.login.LoginView;
import com.example.application.views.login.RegisterComponent;
import com.google.common.base.Optional;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.crypto.password.PasswordEncoder;

@AnonymousAllowed
@Route("footer")
@ParentLayout(MainLayout.class)
public class FooterLayout extends VerticalLayout implements RouterLayout {

    private final Div container = new Div();
    private final Div footer = new Div();    
    private Button loginBtn = new Button("Kirjaudu sisään");
    private Button registerBtn = new Button("Rekisteröidy");
    private Button logoutBtn = new Button("Kirjaudu ulos");
    private final AuthenticatedUser authenticatedUser;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final LoginView loginView;

    public FooterLayout(AuthenticatedUser authenticatedUser, 
                        UserService userService, 
                        PasswordEncoder passwordEncoder,
                        LoginView loginView) {
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.loginView = loginView;
        setSizeFull();
        // setPadding(false);
        // setSpacing(false);
        container.setSizeFull();
        // footer.setWidth("100%");
        // footer.setHeight("min-content");
        footer.addClassName(Gap.MEDIUM);
        loginBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        logoutBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        loginBtn.addClickListener(e -> loginView.setOpened(true));
        RegisterComponent registerComponent = new RegisterComponent(userService, passwordEncoder);
        registerBtn.addClickListener(e -> registerComponent.openRegisterDialog());
        logoutBtn.addClickListener(e -> logOutUser());
        logoutBtn.setVisible(false);
        footer.add(loginBtn, registerBtn, logoutBtn);

        add(container, footer);
        expand(container);

        updateFooter();
    }

    private void updateFooter() {
        
        authenticatedUser.get().ifPresentOrElse(user -> {
            loginBtn.setVisible(false);
            registerBtn.setVisible(false);
            logoutBtn.setVisible(true);
        }, () -> {
            loginBtn.setVisible(true);
            registerBtn.setVisible(true);
            logoutBtn.setVisible(false);
        });
    }
    
    private void logOutUser() {
        authenticatedUser.logout();
        getUI().ifPresent(ui -> ui.navigate(""));
    }

    public void showRouterLayoutContent(HasElement content) {
        container.removeAll();
        container.getElement().appendChild(content.getElement());
    }

}