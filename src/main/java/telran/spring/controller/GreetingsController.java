package telran.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import telran.spring.Person;
import telran.spring.service.GreetingsService;
import telran.spring.service.IdName;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    private static final Logger log = LoggerFactory.getLogger(GreetingsController.class);
    private final GreetingsService greetingsService;

    public GreetingsController(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    @GetMapping("/{id}")
    public String getGreetings(@PathVariable long id) {
        log.debug("Received getGreetings request with id: {}", id);
        return greetingsService.getGreetings(id);
    }

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable long id) {
        log.debug("Received getPerson request with id: {}", id);
        return greetingsService.getPerson(id);
    }

    @GetMapping("/city/{city}")
    public List<Person> getPersonsByCity(@PathVariable String city) {
        log.debug("Received getPersonsByCity request for city: {}", city);
        return greetingsService.getPersonsByCity(city);
    }

    @PostMapping
    public String addName(@RequestBody IdName idName) {
        log.debug("Received addName request with id: {} and name: {}", idName.id(), idName.name());
        return greetingsService.addName(idName);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        log.debug("Received addPerson request for person with id: {}", person.id());
        return greetingsService.addPerson(person);
    }

    @PutMapping
    public String updateName(@RequestBody IdName idName) {
        log.debug("Received updateName request for id: {} with new name: {}", idName.id(), idName.name());
        return greetingsService.updateName(idName);
    }

    @PutMapping("/person")
    public Person updatePerson(@RequestBody Person person) {
        log.debug("Received updatePerson request for person with id: {}", person.id());
        return greetingsService.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    public String deleteName(@PathVariable long id) {
        log.debug("Received deleteName request for id: {}", id);
        return greetingsService.deleteName(id);
    }

    @DeleteMapping("/person/{id}")
    public Person deletePerson(@PathVariable long id) {
        log.debug("Received deletePerson request for id: {}", id);
        return greetingsService.deletePerson(id);
    }
}



//package telran.spring.controller;
//
//import org.springframework.web.bind.annotation.*;
//import telran.spring.Person;
//import telran.spring.service.GreetingsService;
//import telran.spring.service.IdName;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("greetings")
//public class GreetingsController {
//    private final GreetingsService greetingsService;
//
//    public GreetingsController(GreetingsService greetingsService) {
//        this.greetingsService = greetingsService;
//    }
//
//    @GetMapping("{id}")
//    String getGreetings(@PathVariable long id) {
//        return greetingsService.getGreetings(id);
//    }
//
//    @PostMapping
//    String addName(@RequestBody IdName idName) {
//        return greetingsService.addName(idName);
//    }
//
//    @PutMapping
//    String updateName(@RequestBody IdName idName) {
//        return greetingsService.updateName(idName);
//    }
//
//    @DeleteMapping("{id}")
//    String deleteName(@PathVariable long id) {
//        return greetingsService.deleteName(id);
//    }
//
//    // New end points
//    @GetMapping("person/{id}")
//    Person getPerson(@PathVariable long id) {
//        return greetingsService.getPerson(id);
//    }
//
//    @GetMapping("/city/{city}")
//    List<Person> getPersonsByCity(@PathVariable String city) {
//        return greetingsService.getPersonsByCity(city);
//    }
//
//    @PostMapping("/person")
//    Person addPerson(@RequestBody Person person) {
//        return greetingsService.addPerson(person);
//    }
//
//    @DeleteMapping("/person/{id}")
//    Person deletePerson(@PathVariable long id) {
//        return greetingsService.deletePerson(id);
//    }
//
//    @PutMapping("/person")
//    Person updatePerson(@RequestBody Person person) {
//        return greetingsService.updatePerson(person);
//    }
//}
//
////package telran.spring.controller;
////
////import org.springframework.web.bind.annotation.*;
////
////import lombok.RequiredArgsConstructor;
////import telran.spring.service.GreetingsService;
////import telran.spring.service.IdName;
////
////@RestController
////@RequestMapping("greetings")
////@RequiredArgsConstructor
////public class GreetingsController {
////	final GreetingsService greetingsService;
////	@GetMapping("{id}")
////	String getGreetings(@PathVariable long id) {
////		return greetingsService.getGreetings(id);
////	}
////	//TODO update following control end point methods for HW #57 according to updated service
////	@PostMapping
////	String addName(@RequestBody IdName idName) {
////		return greetingsService.addName(idName);
////	}
////	@PutMapping
////	String updateName(@RequestBody IdName idName) {
////		return greetingsService.updateName(idName);
////	}
////	@DeleteMapping("{id}")
////	String deleteName(@PathVariable long id) {
////		return greetingsService.deleteName(id);
////	}
////	//TODO
////	//end points for getting person by ID and getting persons by city see service
////	
////}
