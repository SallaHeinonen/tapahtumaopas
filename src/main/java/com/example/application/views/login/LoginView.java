package com.example.application.views.login;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.login.LoginI18n;
import org.springframework.stereotype.Component;
import com.example.application.security.AuthenticatedUser;


@AnonymousAllowed
@PageTitle("Login")
@Route(value = "login")
@Component
public class LoginView extends LoginOverlay implements BeforeEnterObserver {

    private final AuthenticatedUser authenticatedUser;

    // Login overlay, when user presses the button to log in
    public LoginView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        // setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));
        LoginI18n login18 = LoginI18n.createDefault();
        
        LoginI18n.Header loginHeader = new LoginI18n.Header();
        loginHeader.setTitle("Tapahtumaopas");

        LoginI18n.Form loginForm = login18.getForm();
        loginForm.setTitle("Kirjaudu");
        loginForm.setUsername("Käyttäjänimi");
        loginForm.setPassword("Salasana");
        loginForm.setSubmit("Kirjaudu sisään");
        
        login18.setAdditionalInformation(null);
        login18.setForm(loginForm);
        setI18n(login18);

        setForgotPasswordButtonVisible(false);
        setOpened(false);
    }

    @Override 
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            setOpened(false);
            event.forwardTo("");
        }

        setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }

}
