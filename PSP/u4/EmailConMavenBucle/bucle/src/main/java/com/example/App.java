package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class App {

    final static int NOMBRES_POS = 0;
    final static int DESTINATARIOS_POS = 1;
    final static String SPLIT_CHR = ";";

    public static void main(String[] args) {
        List<String> nombres = new ArrayList<>();
        List<String> destinatarios = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("C:\\Users\\34634\\OneDrive\\Escritorio\\destinatarios.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                nombres.add(line.split(SPLIT_CHR)[NOMBRES_POS]);
                destinatarios.add(line.split(SPLIT_CHR)[DESTINATARIOS_POS]);
            }

        } catch (Exception e) {
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("U: ");
        String user = sc.nextLine();
        System.out.println("C: ");
        String password = sc.nextLine();

        for (int i = 0; i < destinatarios.size(); i++) {
            Email email = EmailBuilder.startingBlank()
                    .to(nombres.get(i), destinatarios.get(i))
                    .from("Ricardo", "rharrisonromero@educa.madrid.org")
                    .withReplyTo("Ricardo", "rharrisonromero@educa.madrid.org")
                    .withSubject("Bucle for to wapo prim")
                    .withHTMLText("<h1>Pedazo de bucle for!!</h1><p>Email enviado con bucle</p>")
                    .buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer("smtp.educa.madrid.org", 587, user, password)
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .clearEmailValidator() // turns off email validation
                    .buildMailer();

            mailer.sendMail(email);
        }

    }
}
