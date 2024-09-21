package ru.muravin.springLibrary.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Transient
    private Integer person_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, message = "ФИО должно занимать не менее 2 символов")
    private String name;

    @Column(name = "year_of_creation")
    @NotNull(message = "Год создания книги не должен быть пустым")
    private int yearOfCreation;

    @Column(name = "author_name")
    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min = 2, message = "Имя автора должно состоять из нескольких символов")
    private String authorName;

    @Column(name="issued_date_time")
    private Date issuedDateTime;

    public Book() {

    }

    public Book(String name, int yearOfCreation, String authorName, Integer personId, Person person) {
        this.name = name;
        this.yearOfCreation = yearOfCreation;
        this.authorName = authorName;
        this.person_id = personId;
        this.person = person;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public Date getIssuedDateTime() {
        return issuedDateTime;
    }

    public void setIssuedDateTime(Date issuedDateTime) {
        this.issuedDateTime = issuedDateTime;
    }
}
