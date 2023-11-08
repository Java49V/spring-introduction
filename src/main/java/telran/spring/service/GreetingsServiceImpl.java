package telran.spring.service;

import org.springframework.stereotype.Service;
import telran.spring.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GreetingsServiceImpl implements GreetingsService {
    Map<Long, String> greetingsMap = new HashMap<>(Map.of(123L, "David", 124L, "Sara", 125L, "Rivka"));
    Map<Long, Person> personsMap = new HashMap<>();

    @Override
    public String getGreetings(long id) {
        String name = greetingsMap.getOrDefault(id, "Unknown Guest");
        return "Hello, " + name;
    }

    @Override
    public String addName(IdName idName) {
        String name = greetingsMap.putIfAbsent(idName.id(), idName.name());
        if (name != null) {
            throw new IllegalStateException(idName.id() + " already exists");
        }
        return idName.name();
    }

    @Override
    public String deleteName(long id) {
        String name = greetingsMap.remove(id);
        if (name == null) {
            throw new IllegalStateException(id + " not found");
        }
        return name;
    }

    @Override
    public String updateName(IdName idName) {
        if (!greetingsMap.containsKey(idName.id())) {
            throw new IllegalStateException(idName.id() + " not found");
        }
        greetingsMap.put(idName.id(), idName.name());
        return idName.name();
    }

    @Override
    public Person getPerson(long id) {
        return personsMap.get(id);
    }

    @Override
    public List<Person> getPersonsByCity(String city) {
        return personsMap.values().stream()
                .filter(person -> person.city().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public Person addPerson(Person person) {
        if (personsMap.containsKey(person.id())) {
            throw new IllegalStateException(person.id() + " already exists");
        }
        personsMap.put(person.id(), person);
        return person;
    }

    @Override
    public Person deletePerson(long id) {
        Person person = personsMap.remove(id);
        if (person == null) {
            throw new IllegalStateException(id + " not found");
        }
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
        if (!personsMap.containsKey(person.id())) {
            throw new IllegalStateException(person.id() + " not found");
        }
        personsMap.put(person.id(), person);
        return person;
    }
}


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


