package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.User;

import javax.sql.DataSource;
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
    private final String SQL_FIND_EMAIL = "SELECT * FROM users where email=?";

    //language=SQL
    private static final  String SQL_INSERT = "INSERT INTO users (name, email, surname, password, profile_img, rights)" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    private final RowMapper<User> UserRowMapper = row -> User.builder()
            .name(row.getString("name"))
            .lastname(row.getString("surname"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .profileImg(row.getString("profile_img"))
            .rights(User.Right.valueOf(row.getString("rights")))
            .build();

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, UserRowMapper);
    }

    @Override
    public User findByNamePassword(String name, String password) {
        List<User> users = template.query(SQL_FIND_NAME_PASS, UserRowMapper, name, password);
        return !users.isEmpty() ? users.get(0) : null;
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = template.query(SQL_FIND_EMAIL, UserRowMapper, email);
        return !users.isEmpty() ? users.get(0) : null;
    }

    @Override
    public void save(User user) {
        template.update(
                SQL_INSERT, user.getName(), user.getEmail(), user.getLastname(), user.getPassword(),
                user.getProfileImg(), user.getRights().getString());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }
}
