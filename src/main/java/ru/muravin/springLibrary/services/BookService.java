package ru.muravin.springLibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.muravin.springLibrary.models.Book;
import ru.muravin.springLibrary.models.Person;
import ru.muravin.springLibrary.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) /// ВСЕ ПУБЛИЧНЫЕ МЕТОДЫ СТАНУТ TRANSACTIONAL
public class BookService {
    private final BooksRepository booksRepository;
    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }
    public Book findOne(int id) {
        Optional<Book> foundPerson = booksRepository.findById(id);
        return foundPerson.orElse(null);
    }
    public List<Book> findByPerson(Person person) {
        return booksRepository.findByPerson(person);
    }
    @Transactional
    public void save(Book person) {
        //person.setCreatedAt(new Date());
        booksRepository.save(person);
    }
    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
