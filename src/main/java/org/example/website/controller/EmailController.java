package org.example.website.controller;


import ch.qos.logback.core.model.Model;
import org.example.website.service.ClientService;
import org.example.website.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService, ClientService clientService) {
        this.emailService = emailService;
    }


    @GetMapping("/email")
    public String email(Model model) {
        return "email";
    }

    @PostMapping("/emailGo")
    public String emailGo(@RequestParam("name") String name,
                          @RequestParam("number") String number,
                          @RequestParam("text") String text,
                          Model model) {
        emailService.sendEmail("prestige_forging83@mail.ru",
                name,
                number,
                text);

        return "redirect:/";
    }
}
