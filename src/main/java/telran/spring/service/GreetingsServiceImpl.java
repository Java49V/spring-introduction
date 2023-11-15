package telran.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.spring.Person;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GreetingsServiceImpl implements GreetingsService {
    private static final Logger log = LoggerFactory.getLogger(GreetingsServiceImpl.class);
    private Map<Long, String> greetingsMap = new HashMap<>();
    private Map<Long, Person> personsMap = new HashMap<>();

    private static final String DATA_FILE = "greetings_data.dat";

    @Override
    public String getGreetings(long id) {
        log.debug("Executing getGreetings for id: {}", id);
        return "Hello, " + greetingsMap.getOrDefault(id, "Guest");
    }

    @Override
    public Person getPerson(long id) {
        log.debug("Fetching person with id: {}", id);
        return personsMap.get(id);
    }

    @Override
    public List<Person> getPersonsByCity(String city) {
        log.debug("Fetching persons from city: {}", city);
        return personsMap.values().stream()
                .filter(person -> person.city().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public String addName(IdName idName) {
        log.debug("Adding name with id: {}", idName.id());
        if (greetingsMap.putIfAbsent(idName.id(), idName.name()) != null) {
            throw new IllegalStateException("ID " + idName.id() + " already exists");
        }
        return idName.name();
    }

    @Override
    public String deleteName(long id) {
        log.debug("Deleting name with id: {}", id);
        String removedName = greetingsMap.remove(id);
        if (removedName == null) {
            throw new IllegalStateException("ID " + id + " not found");
        }
        return removedName;
    }

    @Override
    public String updateName(IdName idName) {
        log.debug("Updating name with id: {}", idName.id());
        if (!greetingsMap.containsKey(idName.id())) {
            throw new IllegalStateException("ID " + idName.id() + " not found");
        }
        greetingsMap.put(idName.id(), idName.name());
        return idName.name();
    }

    @Override
    public Person addPerson(Person person) {
        log.debug("Adding person with id: {}", person.id());
        if (personsMap.putIfAbsent(person.id(), person) != null) {
            throw new IllegalStateException("Person with ID " + person.id() + " already exists");
        }
        return person;
    }

    @Override
    public Person deletePerson(long id) {
        log.debug("Deleting person with id: {}", id);
        Person removedPerson = personsMap.remove(id);
        if (removedPerson == null) {
            throw new IllegalStateException("Person with ID " + id + " not found");
        }
        return removedPerson;
    }

    @Override
    public Person updatePerson(Person person) {
        log.debug("Updating person with id: {}", person.id());
        if (!personsMap.containsKey(person.id())) {
            throw new IllegalStateException("Person with ID " + person.id() + " not found");
        }
        personsMap.put(person.id(), person);
        return person;
    }

    public void save() throws IOException {
        log.debug("Saving data to file");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(personsMap);
        }
    }

    @SuppressWarnings("unchecked")
    public void restore() throws IOException, ClassNotFoundException {
        log.debug("Restoring data from file");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            personsMap = (Map<Long, Person>) ois.readObject();
        }
    }
}

//package telran.spring.service;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import lombok.extern.slf4j.Slf4j;
//import telran.exceptions.NotFoundException;
//import telran.spring.Person;
//@Service
//@Slf4j
//public class GreetingsServiceImpl implements GreetingsService {
//    Map<Long, Person> greetingsMap = new HashMap<>();
//    @Value("${app.greeting.message:Hello}")
//    String greetingMessage;
//    @Value("${app.unknown.name:unknown guest}")
//    String unknownName;
//    @Value("${app.file.name:persons.data}")
//    String fileName;
//	@Override
//	public String getGreetings(long id) {
//		
//		Person person =  greetingsMap.get(id);
//		String name = "";
//		if (person == null) {
//			name = unknownName;
//			log.warn("person with id {} not found", id);
//		} else {
//			name = person.name();
//			log.debug("person name is {}", name);
//		}
//		return String.format("%s, %s", greetingMessage, name);
//	}
//	
//	@Override
//	public Person getPerson(long id) {
//		
//		Person person = greetingsMap.get(id);
//		if(person == null) {
//			log.warn("person with id {} not found", id);
//		} else {
//			log.debug("persons with id {} exists", id);
//		}
//		return person;
//	}
//	@Override
//	public List<Person> getPersonsByCity(String city) {
//		
//		return greetingsMap.values().stream()
//				.filter(p -> p.city().equals(city))
//				.toList();
//	}
//	@Override
//	public Person addPerson(Person person) {
//		long id = person.id();
//		if (greetingsMap.containsKey(id) ){
//			throw new IllegalStateException(String.format("person with id %d already exists", id));
//		}
//		 greetingsMap.put(id, person);
//		 log.debug("person with id {} has been saved", id);
//		 return person;
//	}
//	@Override
//	public Person deletePerson(long id) {
//		if (!greetingsMap.containsKey(id) ){
//			throw new NotFoundException(String.format("person with id %d doesn't exist", id));
//		}
//		Person person =  greetingsMap.remove(id);
//		log.debug("person with id {} has been removed", person.id());
//		return person;
//	}
//	@Override
//	public Person updatePerson(Person person) {
//		long id = person.id();
//		if (!greetingsMap.containsKey(id) ){
//			throw new NotFoundException(String.format("person with id %d doesn't exist", id));
//		}
//		greetingsMap.put(id, person);
//		log.debug("person with id {} has been update", person.id());
//		return person;
//	}
//
//	@Override
//	public void save(String fileName) {
//		// TODO saving persons data into ObjectOutputStream
//		log.info("persons data have been saved");
//		
//	}
//
//	@Override
//	public void restore(String fileName) {
//		// TODO restoring from file using ObjectInputStream
//		log.info("restored from file");
//		
//	}
//	@PostConstruct
//	void restoreFromFile() {
//		restore(fileName);
//		
//	}
//	@PreDestroy
//	void saveToFile() {
//		save(fileName);
//	}
//
//}
//
//
