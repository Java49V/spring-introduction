package telran.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import telran.exceptions.NotFoundException;
import telran.spring.service.GreetingsService;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //defining order of the tests by @Order
class GreetingsServiceTest {
	@Autowired
GreetingsService greetingsService;
	Person personNormal = new Person(123, "Vasya", "Rehovot", "vasya@gmail.com",
    		"054-1234567");
	Person personNormalUpdated = new Person(123, "Vasya", "Lod", "vasya@gmail.com",
    		"054-1234567");
	Person personNotFound = new Person(500, "Vasya", "Rehovot", "vasya@gmail.com",
    		"054-1234567");
	@BeforeAll
	static void deleteFile() throws IOException {
		Files.deleteIfExists(Path.of("test.data"));
	}
	@Test
	@Order(1)
	void loadApplicationContextTest() {
		assertNotNull(greetingsService);
	}
	@Test
	@Order(2)
	void addPersonNormalTest() {
		assertEquals(personNormal, greetingsService.addPerson(personNormal));
	}
	@Test
	@Order(3)
	void addPersonAlreadyExistsTest() {
		assertThrowsExactly(IllegalStateException.class, () -> greetingsService.addPerson(personNormal));
	}
	@Test
	@Order(4)
	void updatePersonNormalTest() {
		assertEquals(personNormalUpdated, greetingsService.updatePerson(personNormalUpdated));
	}
	@Test
	@Order(5)
	void getPersonTest() {
		assertEquals(personNormalUpdated,greetingsService.getPerson(123));
		
	}
	@Test
    @Order(6)
	void getPersonNotFound() {
		assertNull(greetingsService.getPerson(500));
	}
	@Test
	@Order(7)
	void updateNotExistsTest() {
		assertThrowsExactly(NotFoundException.class,
				() -> greetingsService.updatePerson(personNotFound));
	}
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	@Test
	@Order(8)
	void persistenceTest() {
		assertEquals(personNormalUpdated, greetingsService.getPerson(123));
	}
	
	@Test
    @Order(9)
    void deletePersonTest() {
        greetingsService.addPerson(personNormal);
        assertEquals(personNormal, greetingsService.getPerson(123));
        greetingsService.deletePerson(123);
        assertNull(greetingsService.getPerson(123));
    }

    // Example for testing getPersonsByCity
    @Test
    @Order(10)
    void getPersonsByCityTest() {
        greetingsService.addPerson(personNormal);
        List<Person> persons = greetingsService.getPersonsByCity("Rehovot");
        assertFalse(persons.isEmpty());
        assertEquals(1, persons.size());
        assertEquals(personNormal, persons.get(0));
    }
	

}