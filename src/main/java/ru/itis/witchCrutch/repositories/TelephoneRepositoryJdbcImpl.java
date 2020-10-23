package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Telephone;
import ru.itis.witchCrutch.repositories.interfaces.TelephoneRepository;

import javax.sql.DataSource;

public class TelephoneRepositoryJdbcImpl implements TelephoneRepository {

    private final DataSource dataSource;

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO telephone (tel) VALUES (?);";

    public TelephoneRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Telephone telephone) {
        SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);
        template.update(SQL_SAVE, telephone.getTelephone());
    }
}
