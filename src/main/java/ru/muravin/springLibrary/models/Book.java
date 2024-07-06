package ru.muravin.springLibrary.models;

import javax.validation.constraints.*;

public class Book {
    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, message = "ФИО должно занимать не менее 2 символов")
    private String name;

    @NotNull(message = "Год создания книги не должен быть пустым")
    private int yearOfCreation;

    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min = 2, message = "Имя автора должно состоять из нескольких символов")
    private String authorName;

    public Book() {

    }

    public Book(String name, int yearOfCreation, String authorName) {
        this.name = name;
        this.yearOfCreation = yearOfCreation;
        this.authorName = authorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
