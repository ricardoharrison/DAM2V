package com.mavensmtp;

import java.util.Scanner;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Usuario: ");
        String user = sc.nextLine();
        System.out.println("Contraseña: ");
        String password = sc.nextLine();

        Email email = EmailBuilder.startingBlank()
                // .to("Jorge", "jorge.duenas@educa.madrid.org")
                .to("Eloy", "ian.harrisonromero@educa.madrid.org")
                .to("Vitaliy", "vitaliy.popovych@educa.madrid.org")
                .to("Ricardo", "ricardoharrisonromero@gmail.com")
                .from("Ricardo", "rharrisonromero@educa.madrid.org")
                .withReplyTo("Ricardo", "rharrisonromero@educa.madrid.org")
                .withSubject("Firewall o q ase")
                .withHTMLText("<h1>Hola!!</h1><p>Este e-mail se puede enviar con Java y Maven</p>")
                .withPlainText("Este e-mail se puede enviar con Java y Maven")
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.educa.madrid.org", 587, user, password)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .clearEmailValidator() // turns off email validation
                .buildMailer();

        mailer.sendMail(email);
    }
}
