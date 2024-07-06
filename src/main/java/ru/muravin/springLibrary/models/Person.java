package ru.muravin.springLibrary.models;

import javax.validation.constraints.*;

/**
 *
 */
public class Person {
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 30, message = "ФИО может занимать от 2 до 30 символов")
    private String name;

    @NotNull(message = "Год рождения не должен быть пустым")
    @Min(value = 1890, message = "Некорректно указан год рождения")
    private int yearOfBirth;

    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Email должен быть валидным")
    private String email;

    public Person() {

    }

    public Person(int id, String name, int yearOfBirth, String email) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
