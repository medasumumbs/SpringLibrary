package ru.muravin.springLibrary.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.muravin.springLibrary.models.Book;
import ru.muravin.springLibrary.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    public List<Book> findByPerson(Person person);
}
