jpersistence
============

Persistence engine using plain text docs.

###Usage Example

####Extending from Entity

```java
import jpersistence.Entity;

public class Person extends Entity {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
```

####Testing it

```java
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
```