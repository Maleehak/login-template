package com.backend.backend.RegisterAndLoginBackend.gateway;

import com.backend.backend.RegisterAndLoginBackend.entity.NotificationTemplate;
import com.backend.backend.RegisterAndLoginBackend.event.NotificationEvent;
import com.backend.backend.RegisterAndLoginBackend.repository.NotificationTemplateRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.twilio.type.PhoneNumber;

import java.util.Optional;

import static com.backend.backend.RegisterAndLoginBackend.contant.Constants.*;

@Slf4j
@Service
public class SmsGateway {

    @Value("${twilio.sms.sender}")
    private String from;

    @Value("${twilio.sid}")
    public String accountId;

    @Value("${twilio.token}")
    public String token;

    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;


    @Async
    @EventListener
    public void sendSms(NotificationEvent event) {
        try{
            Twilio.init(accountId, token);
            createMessage(event);
        }catch (Exception e){

        }
    }

    private void createMessage(NotificationEvent event){
        String to = event.getAdditional().get(PHONE_NUMBER);

        Optional<NotificationTemplate> template = notificationTemplateRepository.findByTemplateType(event.getType().toString());
        Message
                .creator(new PhoneNumber(to), new PhoneNumber(from), template.get().getSmsBody())
                .create();
    }
}
