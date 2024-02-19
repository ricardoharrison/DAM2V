package com.mavensmtp;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class App {
    public static void main(String[] args) {

        Email email = EmailBuilder.startingBlank()
                .to("Jorge", "jorge.duenas@educa.madrid.org")
                .to("Eloy", "ian.harrisonromero@educa.madrid.org")
                .to("Vitaliy", "vitaliy.popovych@educa.madrid.org")
                .to("Ricardo", "ricardoharrisonromero@gmail.com")
                .from("Ricardo", "rharrisonromero@educa.madrid.org")
                .withReplyTo("Ricardo", "rharrisonromero@educa.madrid.org")
                .withSubject("Mail enviado desde Java")
                .withHTMLText("<h1>Hola!!</h1><p>Este e-mail se puede enviar con Java y Maven</p>")
                .withPlainText("Este e-mail se puede enviar con Java y Maven")
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.educa.madrid.org", 587, "alumno.falso", "SECRETO")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .clearEmailValidator() // turns off email validation
                .buildMailer();

        mailer.sendMail(email);
    }
}
