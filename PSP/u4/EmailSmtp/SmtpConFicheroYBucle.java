package u4.EmailSmtp;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SmtpConFicheroYBucle {
    public static void main(String[] args) {

        // Load email configuration
        ConfigLoader.loadProperties("simplejavamail.properties");

        // File path to read names and addresses
        String filePath = "recipients.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            Email email = EmailBuilder.startingBlank()
                    .from("Jorge", "jorge.duenas@educa.madrid.org")
                    .withReplyTo("Jorge", "jorge.duenas@educa.madrid.org")
                    .withSubject("Proyecto Spamtoso")
                    .withHTMLText("<h1>Hola!!</h1><p>¿Qué tal?</p>")
                    .withPlainText("Hola! ¿Qué tal?")
                    .buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer("smtp.educa.madrid.org", 587, "alumno.falso", "SECRETO")
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .clearEmailValidator() // turns off email validation
                    .buildMailer();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    email.addNamedToRecipient(parts[0].trim(), parts[1].trim());
                }
            }

            mailer.sendMail(email);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
