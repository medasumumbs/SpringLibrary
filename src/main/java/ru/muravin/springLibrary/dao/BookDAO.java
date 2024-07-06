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
    private final JdbcTemplate jdbcTemplate;
    private static Connection connection;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from Book where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("Insert into Book  (name,year_of_creation,author_name) values " +
                "(?,?,?)", book.getName(),book.getYearOfCreation(),book.getAuthorName());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update(
                "update Book set name=?, year_of_creation=?, author_name=? where id=?",
                updatedBook.getName(), updatedBook.getYearOfCreation(), updatedBook.getAuthorName(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from Book  where id=?", id);

    }
}
