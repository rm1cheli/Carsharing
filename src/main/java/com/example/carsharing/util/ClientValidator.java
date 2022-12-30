package com.example.carsharing.util;

import com.example.carsharing.models.Client;
import com.example.carsharing.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ClientValidator implements Validator {
    private final ClientService clientService;

    @Autowired
    public ClientValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        Optional<Client> cl = clientService.findByName(client.getName());
        if (cl.isPresent()) {
            errors.rejectValue("name", "", "человек с таким именем уже есть в списке клиентов");
        }
    }
}
