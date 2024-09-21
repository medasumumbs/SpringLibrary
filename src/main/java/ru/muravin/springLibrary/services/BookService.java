package ru.muravin.springLibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Book> findAll(Pageable pageable) {
        return booksRepository.findAll(pageable).toList();
    }
    public List<Book> findAll(Sort sort) {
        return booksRepository.findAll(sort);
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

    public List<Book> findByNameStartingWith(String name) {
        return booksRepository.findByNameStartingWith(name);
    }
    @Transactional
    public void save(Book book) {

        //person.setCreatedAt(new Date());
        booksRepository.save(book);
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
