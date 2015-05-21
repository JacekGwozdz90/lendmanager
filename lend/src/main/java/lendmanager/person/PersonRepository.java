package lendmanager.person;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    public Person findByFirstName(String firstName);
    public Person findByLastName(String lastName);
    
}