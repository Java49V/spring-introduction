package telran.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.spring.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GreetingsServiceImpl implements GreetingsService {
    private static final Logger log = LoggerFactory.getLogger(GreetingsServiceImpl.class);
    private Map<Long, String> greetingsMap = new HashMap<>();
    private Map<Long, Person> personsMap = new HashMap<>();

    @Override
    public String getGreetings(long id) {
        log.debug("Executing getGreetings for id: {}", id);
        String name = greetingsMap.getOrDefault(id, "Guest");
        return "Hello, " + name;
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
}


//package telran.spring.service;
//
//import org.springframework.stereotype.Service;
//import telran.spring.Person;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class GreetingsServiceImpl implements GreetingsService {
//	Map<Long, String> greetingsMap = new HashMap<>(Map.of(123L, "David", 124L, "Sara", 125L, "Rivka"));
//	Map<Long, Person> personsMap = new HashMap<>();
//
//	@Override
//	public String getGreetings(long id) {
//		String name = greetingsMap.getOrDefault(id, "Unknown Guest");
//		return "Hello, " + name;
//	}
//
//	@Override
//	public String addName(IdName idName) {
//		String name = greetingsMap.putIfAbsent(idName.id(), idName.name());
//		if (name != null) {
//			throw new IllegalStateException(idName.id() + " already exists");
//		}
//		return idName.name();
//	}
//
//	@Override
//	public String deleteName(long id) {
//		String name = greetingsMap.remove(id);
//		if (name == null) {
//			throw new IllegalStateException(id + " not found");
//		}
//		return name;
//	}
//
//	@Override
//	public String updateName(IdName idName) {
//		if (!greetingsMap.containsKey(idName.id())) {
//			throw new IllegalStateException(idName.id() + " not found");
//		}
//		greetingsMap.put(idName.id(), idName.name());
//		return idName.name();
//	}
//
////    @Override
////    public Person getPerson(long id) {
////        return personsMap.get(id);
////    }
//
//	@Override
//	public Person getPerson(long id) {
//		Person person = personsMap.get(id);
//		if (person == null) {
//			throw new IllegalArgumentException("Person with ID " + id + " not found");
//		}
//		return person;
//	}
//
//	@Override
//	public List<Person> getPersonsByCity(String city) {
//		return personsMap.values().stream().filter(person -> person.city().equalsIgnoreCase(city))
//				.collect(Collectors.toList());
//	}
//
////	@Override
////	public Person addPerson(Person person) {
////		if (personsMap.containsKey(person.id())) {
////			throw new IllegalStateException(person.id() + " already exists");
////		}
////		personsMap.put(person.id(), person);
////		return person;
////	}
//	@Override
//	public Person addPerson(Person person) {
//		Person existingPerson = personsMap.putIfAbsent(person.id(), person);
//		if (existingPerson != null) {
//			throw new IllegalStateException(person.id() + " already exists");
//		}
//		return person;
//	}
//
//	@Override
//	public Person deletePerson(long id) {
//		Person person = personsMap.remove(id);
//		if (person == null) {
//			throw new IllegalStateException(id + " not found");
//		}
//		return person;
//	}
//
//	@Override
//	public Person updatePerson(Person person) {
//		if (!personsMap.containsKey(person.id())) {
//			throw new IllegalStateException(person.id() + " not found");
//		}
//		personsMap.put(person.id(), person);
//		return person;
//	}
//}

//package telran.spring.service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Service;
//@Service
//public class GreetingsServiceImpl implements GreetingsService {
//    Map<Long, String> greetingsMap = new HashMap<>(Map.of(123l, "David", 124l, "Sara",125l, "Rivka"));
//	@Override
//	public String getGreetings(long id) {
//		
//		String name =  greetingsMap.getOrDefault(id, "Unknown Guest");
//		return "Hello, " + name;
//	}
//	@Override
//	public String addName(IdName idName) {
//		String name = greetingsMap.putIfAbsent(idName.id(), idName.name());
//		if(name != null) {
//			throw new IllegalStateException(idName.id() + " already exists");
//		}
//		return idName.name();
//	}
//	@Override
//	public String deleteName(long id) {
//		String name = greetingsMap.remove(id);
//		if(name == null) {
//			throw new IllegalStateException(id + " not found");
//		}
//		return name;
//	}
//	@Override
//	public String updateName(IdName idName) {
//		if(!greetingsMap.containsKey(idName.id())) {
//			throw new IllegalStateException(idName.id() + " not found");
//		}
//		greetingsMap.put(idName.id(), idName.name());
//		return idName.name();
//	}
//
//}
