package ru.muravin.springLibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.muravin.springLibrary.models.Person;
import ru.muravin.springLibrary.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) /// ВСЕ ПУБЛИЧНЫЕ МЕТОДЫ СТАНУТ TRANSACTIONAL
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }
    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }
    public Optional<Person> findFirstByName(String name) {
        List<Person> people = peopleRepository.findByName(name);
        if (people.isEmpty()) return Optional.empty();
        return Optional.ofNullable(people.get(0));
    }

    @Transactional
    public void save(Person person) {
        //person.setCreatedAt(new Date());
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
