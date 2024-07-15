package ru.muravin.springLibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.muravin.springLibrary.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author
 */
@Component
public class PersonDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private static Connection connection;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from public.\"Person\"", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String email) {
        return jdbcTemplate.query(
                "SELECT * FROM public.\"Person\" where email=?",
                new Object[] {email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from public.\"Person\" where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("Insert into public.\"Person\"  (name,yearOfBirth,email) values " +
                "(?,?,?)", person.getName(),person.getYearOfBirth(),person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update(
                "update public.\"Person\" set name=?, yearOfBirth=?, email=? where id=?",
                updatedPerson.getName(), updatedPerson.getYearOfBirth(), updatedPerson.getEmail(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from public.\"Person\"  where id=?", id);

    }

    public Optional<Object> getPersonByName(String name) {
        return Optional.ofNullable(jdbcTemplate.query("select * from public.\"Person\" where name = ?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null));
    }
}
