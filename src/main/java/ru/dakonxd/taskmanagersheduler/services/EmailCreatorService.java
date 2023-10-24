package ru.dakonxd.taskmanagersheduler.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dakonxd.taskmanagersheduler.interfaces.EmailCreator;
import ru.dakonxd.taskmanagersheduler.model.User;
import ru.dakonxd.taskmanagersheduler.model.dto.EmailDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailCreatorService {

    private final EmailCreator emailCreator;

    public List<EmailDto> getEmailMessages(List<User> users) {
        List<EmailDto> emailMessages = new ArrayList<>();

        for (User user : users) {
            EmailDto emailDTO = new EmailDto();
            emailDTO.setRecipientAddress(emailCreator.createEmailAddress(user));
            emailDTO.setTitle(emailCreator.createEmailTitle(user));
            emailDTO.setText(emailCreator.createEmailText(user));
            emailMessages.add(emailDTO);
        }
        return emailMessages;
    }
}