package lendmanager.person;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, Integer> {

    public List<Person> findByFirstName(String firstName);
    public List<Person> findByLastName(String lastName);
    public List<Person> findByFirstNameAndLastName(String firstName, String lastName);
    public Person findByAccountId(String accountId);
    
}