package ru.dakonxd.taskmanagersheduler.services;

import org.springframework.stereotype.Service;
import ru.dakonxd.taskmanagersheduler.interfaces.EmailCreator;
import ru.dakonxd.taskmanagersheduler.model.Task;
import ru.dakonxd.taskmanagersheduler.model.User;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskReportEmailCreator implements EmailCreator {
    @Override
    public String createEmailAddress(User user) {
        return user.getEmail();
    }

    @Override
    public String createEmailTitle(User user) {
        return "Report on " + user.getUsername() + "task performance for "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }

    @Override
    public String createEmailText(User user) {
        var emailText = new StringBuilder();
        addUncompletedTasksInfo(emailText, user.getTasks());
        return emailText.toString();
    }


    private void addUncompletedTasksInfo(StringBuilder email, List<Task> tasks) {
        var uncompletedTaskHeaders = tasks.stream()
                .filter(Task::isActive)
                .map(Task::getName)
                .collect(Collectors.toList());

        var uncompletedTasksCount = uncompletedTaskHeaders.size();
        if(uncompletedTasksCount > 0) {
            email.append("Total count of uncompleted tasks: " + uncompletedTasksCount)
                    .append("!\n\nList of uncompleted tasks (no more than 5 are displayed):\n");
            for(var i = 0; i < uncompletedTasksCount; i++) {
                if(i>4) {
                    break;
                }
                email.append(" " + uncompletedTaskHeaders.get(i) + ",");
            }
        }
    }
}
