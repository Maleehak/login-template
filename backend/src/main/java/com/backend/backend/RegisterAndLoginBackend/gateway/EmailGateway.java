package com.backend.backend.RegisterAndLoginBackend.gateway;


import com.backend.backend.RegisterAndLoginBackend.entity.NotificationTemplate;
import com.backend.backend.RegisterAndLoginBackend.event.NotificationEvent;
import com.backend.backend.RegisterAndLoginBackend.repository.NotificationTemplateRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Optional;

import static com.backend.backend.RegisterAndLoginBackend.contant.Constants.FIRST_NAME;
import static com.backend.backend.RegisterAndLoginBackend.contant.Constants.LAST_NAME;

@Slf4j
@Service
public class EmailGateway {

    public static final String NAME = "name";
    public static final String MESSAGE = "message";
    public static final String SUBJECT = "subject";
    private static final String EMAIL_TEMPLATE= "emailTemplate";
    public static final String SPACE = " ";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;

    @Value("${email.sender}")
    private String from;

    @Async
    @EventListener
    public void sendEmailNotification(NotificationEvent event) {
        try{
            MimeMessage email = createEmail(event);
            mailSender.send(email);
        }catch (Exception e){
         log.error("Some exception occurred while send email to {}. Exception: {}", event.getTo(), e);
        }
    }

    private MimeMessage createEmail(NotificationEvent event) throws MessagingException {
        Optional<NotificationTemplate> template = notificationTemplateRepository.findByTemplateType(event.getType().toString());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String name = event.getAdditional().get(FIRST_NAME) + SPACE + event.getAdditional().get(LAST_NAME);

        Context context = new Context();
        context.setVariable(NAME, name);
        context.setVariable(MESSAGE, template.get().getEmailBody());
        context.setVariable(SUBJECT, template.get().getEmailTitle());

        String htmlContent = templateEngine.process(EMAIL_TEMPLATE, context);

        helper.setTo(event.getTo());
        helper.setFrom(from);
        helper.setSubject(template.get().getEmailTitle());
        helper.setText(htmlContent, true);
        return message;
    }
}
