package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Message;
import ru.itis.witchCrutch.models.User;

import java.util.List;

public interface MessageService {
    void uploadMessage(Message message);
    List<Message> userMessage(User user);
}

