package ru.muravin.springLibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ru.muravin.springLibrary.dao.BookDAO;
import ru.muravin.springLibrary.dao.PersonDAO;
import ru.muravin.springLibrary.models.Book;
import ru.muravin.springLibrary.models.Person;
import ru.muravin.springLibrary.services.BookService;
import ru.muravin.springLibrary.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BookController {
    private final PeopleService peopleService;
    private final PersonDAO personDAO;

    private final BookService bookService;
    //private final BookDAO bookDAO;

    @Autowired
    public BookController(PeopleService peopleService, PersonDAO personDAO, BookDAO bookDAO, BookService bookService) {
        this.peopleService = peopleService;
        this.personDAO = personDAO;
        //this.bookDAO = bookDAO;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false, defaultValue = "false") Boolean sortByYear) {
        if ((page != null) && (booksPerPage != null)) {
            Pageable pageable;
            if (sortByYear) {
                pageable = PageRequest.of(page, booksPerPage, Sort.by("yearOfCreation"));
            } else {
                pageable = PageRequest.of(page, booksPerPage);
            }
            model.addAttribute("books", bookService.findAll(pageable));
        } else {
            if (sortByYear) {
                model.addAttribute("books", bookService.findAll(Sort.by("yearOfCreation")));
            } else {
                model.addAttribute("books", bookService.findAll());
            }
        }
        return "books/index";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(value = "search_pattern", required = false) String searchPattern) {
        if (searchPattern != null) {
            model.addAttribute("books", bookService.findByNameStartingWith(searchPattern));
            model.addAttribute("search_pattern", searchPattern);
        } else {
            model.addAttribute("search_pattern", null);
        }
        return "books/search";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        if (book.getPerson_id() == null) {
            model.addAttribute("people", peopleService.findAll());
        } else {
            model.addAttribute("person", null);
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookService.update(id, book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/setPerson")
    public String setPerson(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {

        Book bookFromBase = bookService.findOne(id);
        bookService.setPerson(bookFromBase, person);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/free")
    public String setPersonNull(@ModelAttribute("person") Person person,
                            @PathVariable("id") int id) {
        Book bookFromBase = bookService.findOne(id);
        bookService.setPerson(bookFromBase, null);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
