package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.interfaces.RowMapper;
import ru.itis.witchCrutch.repositories.interfaces.UsersRepository;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from users";

    //language=SQL
    private final String SQL_FIND_NAME_PASS = "SELECT * FROM users where name= ? AND password= ?";

    //language=SQL
    private final String SQL_FIND_EMAIL_PASS = "SELECT * FROM users where email=? AND password=?";

    //language=SQL
    private final String SQL_FIND_EMAIL = "SELECT * FROM users where email=?";

    //language=SQL
    private final String SQL_UPDATE = "UPDATE users set name=?, email=?, surname=?, password=?, profile_img=?, rights=? where id=?";

    //language=SQL
    private final String SQL_FIND_ID = "SELECT * FROM users where id=?";

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO users (name, email, surname, password, profile_img, rights)" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    private final RowMapper<User> UserRowMapper = row -> User.builder()
            .id(row.getInt("id"))
            .name(row.getString("name"))
            .lastname(row.getString("surname"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .profileImg(row.getBytes("profile_img") != null ? new ByteArrayInputStream(row.getBytes("profile_img")) : null)
            .rights(User.Right.valueOf(row.getString("rights")))
            .build();

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, UserRowMapper);
    }

    @Override
    public User findByEmailPassword(String email, String password) {
        List<User> users = template.query(SQL_FIND_EMAIL_PASS, UserRowMapper, email, password);
        return !users.isEmpty() ? users.get(0) : null;
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = template.query(SQL_FIND_EMAIL, UserRowMapper, email);
        return !users.isEmpty() ? users.get(0) : null;
    }

    @Override
    public boolean authUser(String email, String hash) {
        List<User> users = template.query(SQL_FIND_EMAIL_PASS, UserRowMapper, email, hash);
        System.out.println(users);
        return !users.isEmpty();
    }

    @Override
    public User findById(int id) {
        List<User> users = template.query(SQL_FIND_ID, UserRowMapper, id);
        return !users.isEmpty() ? users.get(0) : null;
    }

    @Override
    public void save(User user) {
        template.update(
                SQL_INSERT, user.getName(), user.getEmail(), user.getLastname(), user.getPassword(),
                user.getProfileImg(), user.getRights().getString());
    }

    @Override
    public void updateUser(User user) {
        template.update(
                SQL_UPDATE, user.getName(), user.getEmail(), user.getLastname(), user.getPassword(),
                user.getProfileImg(), user.getRights().getString(), user.getId());
    }
}
