package ru.dakonxd.taskmanagersheduler.interfaces;

import ru.dakonxd.taskmanagersheduler.model.User;

public interface EmailCreator {

    String createEmailAddress(User user);

    String createEmailTitle(User user);

    String createEmailText(User user);
}
