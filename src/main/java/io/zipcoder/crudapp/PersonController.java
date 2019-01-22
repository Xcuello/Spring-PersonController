package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {


    @Autowired
    PersonRepository personRepository;

    public PersonController() {

    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {

        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable Integer id) {

        Person p = personRepository.findOne(id);

        if (p == null) {

            return new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Person>> getPersonList() {

        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person updatedPerson) {

        Person p = personRepository.findOne(id);

        if (p != null) {

            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        }

        updatedPerson.setId(id);

        personRepository.save(updatedPerson);

        return new ResponseEntity<>(updatedPerson, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeletePerson(@PathVariable Integer id) {

        personRepository.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}

