package com.example.lab1.services;

import javax.enterprise.inject.Specializes;
import javax.faces.view.ViewScoped;

@Specializes
@ViewScoped
public class LocalEmailSendService extends EmailSendService {
    @Override
    public void sendEmail() {
        System.out.println("Email to: " + this.author.getEmail());
        System.out.println("Subject: Feedback from readers");
        System.out.println("Message: " + this.messageContent);
    }
}
