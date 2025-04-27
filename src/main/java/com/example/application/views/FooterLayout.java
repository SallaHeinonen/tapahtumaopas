package com.example.application.views;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.application.data.User;
import com.example.application.security.AuthenticatedUser;
import com.example.application.services.UserService;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route("")
@ParentLayout(MainLayout.class)
public class FooterLayout extends VerticalLayout implements RouterLayout {

    private final Div container = new Div();
    private final Div footer = new Div();    
    private final Button loginBtn = new Button("Kirjaudu sisään");
    private final Button registerBtn = new Button("Rekisteröidy");
    private final Button logoutBtn = new Button("Kirjaudu ulos");
    @Autowired
    private AuthenticatedUser authenticatedUser;

    public FooterLayout() {
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
        
        footer.add(loginBtn, registerBtn);
        footer.add(logoutBtn);
    
        add(container, footer);
        expand(container);
    }

    public void showRouterLayoutContent(HasElement content) {
        container.removeAll();
        container.getElement().appendChild(content.getElement());
    }

    private void updateFooter() {
        footer.removeAll();
        authenticatedUser.get().ifPresentOrElse(
            user -> footer.add(logoutBtn),
            () -> footer.add(loginBtn, registerBtn)
        );
    }


}

   /* 
     authenticatedUser.get().ifPresentOrElse(
            user -> {
                logoutBtn.addClickListener(e -> {
                    authenticatedUser.logout();
                    updateFooter();
                });
                footer.add(logoutBtn);
            },
            () -> {
                footer.add(loginBtn, registerBtn);
            });
        // Optional<User> maybeUser = authenticatedUser.get();
        // if (maybeUser.isPresent()) {
        //     logoutBtn.addClickListener(e -> {
        //         authenticatedUser.logout();
        //     });
        //     footer.add(logoutBtn);
        // } else {
        //     footer.add(loginBtn, registerBtn);
        // }


     *  private void setupFooter() {
        footer.setWidth("100%");
        footer.setHeight("min-content");
        footer.addClassName(Gap.MEDIUM);
        loginBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        footer.add(loginBtn, registerBtn);
        getElement().appendChild(footer.getElement());
    }
     */

