package com.example.application.views.login;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.application.data.User;
import com.example.application.services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Component
public class RegisterComponent extends Div {

    private Dialog registerDialog;

    public RegisterComponent(UserService userService, PasswordEncoder passwordEncoder) {
        registerDialog = new Dialog();
        registerDialog.setHeaderTitle("Rekisteröidy");
        User user = new User();

        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        // dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        // dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");
        TextField userNameField = new TextField("Käyttäjänimi");
        TextField firstNameField = new TextField("Etunimi");
        TextField lastNameField = new TextField("Sukunimi");
        PasswordField passwordField = new PasswordField("Salasana");
        PasswordField confirmPasswordField = new PasswordField("Salasana uudelleen");
        dialogLayout.add(userNameField, firstNameField, lastNameField, passwordField, confirmPasswordField);
        registerDialog.add(dialogLayout);

        Button saveBtn = new Button("Tallena");
        Button cancelBtn = new Button("Peruuta");
        registerDialog.getFooter().add(saveBtn);
        registerDialog.getFooter().add(cancelBtn);

        add(registerDialog);
        
        // Datan sitominen useriin
        BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);

        binder.forField(userNameField).asRequired("Pakollinen kenttä")
            .withValidator(userService::userNameAvailable, "Tämä tunnus on jo käytössä")
            .bind(User::getUsername, User::setUsername);
        binder.forField(firstNameField).asRequired("Pakollinen kenttä")
            .bind(User::getFirstName, User::setFirstName);
        binder.forField(passwordField).asRequired("Salasana on pakollinen")
            .withValidator(pw -> pw.length() >= 8, "Salasanan minimimäärä on 8 merkkiä")
            .bind(User::getHashedPassword,
                    (user1, pw) -> user1.setHashedPassword(passwordEncoder.encode(pw)));
        binder.forField(confirmPasswordField).asRequired("Salasana on pakollinen")
            .withValidator(confirmed -> Objects.equals(confirmed, passwordField.getValue()),
                "Salasanat eivät täsmää")
            .bind(User::getHashedPassword, 
                (user1, pw) -> user1.setHashedPassword(passwordEncoder.encode(pw)));

        saveBtn.addClickListener(e -> {
            binder.validate();
            if (binder.isValid()) {
            } try {
                binder.writeBean(user);
                userService.save(user);
                UI.getCurrent().navigate("login");
                registerDialog.close();
            } catch (ValidationException ex) {
                throw new RuntimeException(ex);
            }
        });

        cancelBtn.addClickListener(e -> {
            userNameField.clear();
            firstNameField.clear();
            lastNameField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
            registerDialog.close();
        });
    }

    public void openRegisterDialog() {
        registerDialog.open();
    }

}
