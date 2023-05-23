package com.example.lab1.services;

import com.example.lab1.entities.Author;
import com.example.lab1.persistence.AuthorsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

@Named
@ViewScoped
public class EmailSendService implements Serializable {

    @Getter @Setter
    protected String messageContent;

    @Getter @Setter
    protected Author author;

    @Inject
    private AuthorsDAO authorsDAO;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long authorId = Long.parseLong(requestParameters.get("authorId"));
        this.author = authorsDAO.findOne(authorId);
    }

    public void sendEmail() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vaziuojam.uab@gmail.com", "<password>");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("vaziuojam.uab@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(this.author.getEmail())
            );
            message.setSubject("Feedback from readers");
            message.setText(this.messageContent);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
