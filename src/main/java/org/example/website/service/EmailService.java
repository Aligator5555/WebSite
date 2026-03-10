package org.example.website.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendEmail(String to, String name, String number, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("oleg_novak_98@mail.ru");
        message.setTo(to);
        message.setSubject("Клиент");
        message.setText( "Имя: " + name + "\n" +
                "Телефон: " + number + "\n" +
                "Обращение клиента: " + text);
        mailSender.send(message);

    }
}
