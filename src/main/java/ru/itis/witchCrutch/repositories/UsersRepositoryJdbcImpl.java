package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from users";

    //language=SQL
    private final String SQL_FIND_NAME_PASS = "SELECT * FROM users where name= ? AND password= ?";

    //language=SQL
    private static final  String SQL_INSERT = "INSERT INTO users (name, email, surname, password, profile_img, rights)" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    private RowMapper<User> userRowMapper = row -> User.builder()
            .name(row.getString("name"))
            .surname(row.getString("surname"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .profileImg(row.getString("profile_img"))
            .rights(User.Right.valueOf(row.getString("rights")))
            .build();


    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() {
        SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        return simpleJdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public User findByNamePassword(String name, String password) {
        SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        List<User> user = simpleJdbcTemplate.query(SQL_FIND_NAME_PASS, userRowMapper, name, password);

        return !user.isEmpty() ? user.get(0) : null;

    }

    @Override
    public void save(User user) {
        SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        simpleJdbcTemplate.update(
                SQL_INSERT, user.getName(), user.getEmail(), user.getSurname(), user.getPassword(),
                user.getProfileImg(), user.getRights().getString());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }
}
