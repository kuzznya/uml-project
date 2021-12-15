package com.github.kuzznya.bp.notificationservice.service;

import com.github.kuzznya.bp.notificationservice.config.EmailProperties;
import com.github.kuzznya.bp.notificationservice.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSenderService {

    private final UserService userService;
    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;

    @RabbitListener(queuesToDeclare = @Queue(
            name = "notifications.email",
            durable = "true",
            exclusive = "false"))
    public void send(Notification notification) {
        try {
            send(userService.getEmailById(notification.getId()), notification.getTitle(), notification.getMessage());
            log.info("User {} notified: {}", notification.getId(), notification.getTitle());
        } catch (Exception e) {
            log.error("Cannot send notification");
        }
    }

    public void send(String email, String title, String message) {
        if (!new EmailValidator().isValid(email, null))
            throw new IllegalArgumentException("Email should be valid");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(emailProperties.getFrom());
        mail.setTo(email);
        mail.setSubject(title);
        mail.setText(message);
        mailSender.send(mail);
    }
}
