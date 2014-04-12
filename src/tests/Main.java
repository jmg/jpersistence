package tests;

import static org.junit.Assert.*;

import jpersistence.EntityDao;
import jpersistence.EntityList;


import org.junit.Test;

public class Main {

	@Test
	public void testSaveAndFind() {
		
		Person person = new Person();
		
		person.setName("Juan");
		person.setAge(25);
		
		person.save();
		
		EntityList<Person> testPersons = new EntityDao<Person>().findAll(Person.class);		
		Person testPerson = testPersons.get(0);
				
		assertEquals("Juan", testPerson.getName());
		assertEquals(25, testPerson.getAge());
		assertEquals(Integer.valueOf(1), testPerson.getId());
	}

}
