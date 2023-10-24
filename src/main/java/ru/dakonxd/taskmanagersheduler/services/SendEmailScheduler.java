package ru.dakonxd.taskmanagersheduler.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.dakonxd.taskmanagersheduler.model.User;
import ru.dakonxd.taskmanagersheduler.repositories.UserRepository;
import ru.dakonxd.taskmanagersheduler.services.rabbitmq.RabbitProducer;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailScheduler {
    private final UserRepository userRepository;
    private final EmailCreatorService emailCreatorService;
    private final RabbitProducer rabbitProducer;

    @Scheduled(cron = "midnight")
    public void sendEmails() {
        var users = userRepository.findAll();
        var emailMessages = emailCreatorService.getEmailMessages(users);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            var json = objectMapper.writeValueAsString(emailMessages);
            rabbitProducer.sendMessage(json);
        } catch (JsonProcessingException e) {
            log.error("Scheduled task not sent. ", e.getMessage());
        }

    }
}
