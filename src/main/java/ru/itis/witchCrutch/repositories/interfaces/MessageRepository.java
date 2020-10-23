package ru.itis.witchCrutch.repositories.interfaces;

import ru.itis.witchCrutch.models.Message;
import ru.itis.witchCrutch.models.User;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message> {
    List<Message> getUserMessage(User user);
}
