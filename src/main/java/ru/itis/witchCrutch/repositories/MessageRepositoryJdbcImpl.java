package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Message;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.interfaces.MessageRepository;
import ru.itis.witchCrutch.repositories.interfaces.RowMapper;
import ru.itis.witchCrutch.services.interfaces.UsersService;

import javax.sql.DataSource;
import java.util.List;

public class MessageRepositoryJdbcImpl implements MessageRepository {

    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;
    private UsersService userService;

    //language=SQL
    private final String SQL_SAVE = "insert into message (sender_id, text, time, receiver_id, filename) values (?, ?, ?, ?, ?)";

    //language=SQL
    private final String SQL_FIND_USER = "select * from message where sender_id=? order by time desc";

    public MessageRepositoryJdbcImpl(DataSource dataSource, UsersService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    public RowMapper<Message> messageRowMapper = row -> Message.builder()
            .message(row.getString("text"))
            .filename(row.getString("filename"))
            .date(row.getTimestamp("time"))
            .sender(userService.getUserById(row.getInt("sender_id")))
            .receiver(userService.getUserById(row.getInt("receiver_id")))
            .build();

    @Override
    public void save(Message entity) {
        template.update(SQL_SAVE, entity.getSender().getId(), entity.getMessage(), entity.getDate(), entity.getReceiver().getId(), entity.getFilename());
    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void delete(Message entity) {

    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public List<Message> getUserMessage(User user) {
        List<Message> messages = template.query(SQL_FIND_USER, messageRowMapper, user.getId());
        return !messages.isEmpty() ? messages : null;
    }
}
