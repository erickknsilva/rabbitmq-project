package com.erickk2ck.notificacaoservice;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {


    private final JavaMailSender javaMail;

    public void sendEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = javaMail.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("Pedido RabbitMQ <erickdecker23@gmail.com>");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            javaMail.send(message);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar e-mail HTML: " + e.getMessage());
        }
    }

}
