package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Message;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.interfaces.MessageRepository;
import ru.itis.witchCrutch.services.interfaces.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void uploadMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> userMessage(User user) {
        return messageRepository.getUserMessage(user);
    }
}
