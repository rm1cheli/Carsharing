package com.example.carsharing.controllers;

import com.example.carsharing.models.Client;
import com.example.carsharing.services.RegistrationService;
import com.example.carsharing.util.ClientValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final ClientValidator clientValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(ClientValidator clientValidator, RegistrationService registrationService) {
        this.clientValidator = clientValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("client") Client client){
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("client") @Valid Client client,
                                      BindingResult bindingResult){
        clientValidator.validate(client,bindingResult);
        if (bindingResult.hasErrors()){
            return "/auth/registration";
        }
        registrationService.register(client);
        return "redirect:/auth/login";
    }
}
