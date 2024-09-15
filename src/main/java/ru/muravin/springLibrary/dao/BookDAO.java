package ru.muravin.springLibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.muravin.springLibrary.models.Book;

import java.sql.Connection;
import java.util.List;

@Component
public class BookDAO {
    @Autowired
    private final PersonDAO personDAO;
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private static Connection connection;

    public BookDAO(PersonDAO personDAO, JdbcTemplate jdbcTemplate) {
        this.personDAO = personDAO;
        this.jdbcTemplate = jdbcTemplate;
    }
}
