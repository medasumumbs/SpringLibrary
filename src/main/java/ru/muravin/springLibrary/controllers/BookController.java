package ru.muravin.springLibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.muravin.springLibrary.dao.BookDAO;
import ru.muravin.springLibrary.dao.PersonDAO;
import ru.muravin.springLibrary.models.Book;
import ru.muravin.springLibrary.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public BookController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = bookDAO.show(id);
        model.addAttribute("book", book);
        if (book.getPerson_id() == null) {
            model.addAttribute("people", personDAO.index());
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

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/setPerson")
    public String setPerson(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
        bookDAO.setPerson(id, person.getId());
        return "redirect:/books";
    }
    @PatchMapping("/{id}/free")
    public String setPersonNull(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                            @ModelAttribute("person") Person person,
                            @PathVariable("id") int id) {
        bookDAO.setPerson(id, null);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
