package lendmanager.items;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    public List<Item> findByName(String name);

    public List<Item> findByPersonId(Integer personId);
    
    public List<Item> findByPersonFacebookId(Integer personFacebookId);
    
    public List<Item> findByPersonLastName(String lastName);
    
    public List<Item> findByPersonFirstName(String firstName);
    
    public List<Item> findByPersonFirstNameAndPersonLastName(String firstName, String lastName);
    
    public List<Item> findByNameAndPersonFirstNameAndPersonLastName(String name, String firstName, String lastName);

	public List<Item> findByOwnerId(Integer ownerId);
	
	public List<Item> findByOwnerFacebookId(Integer ownerId);

	public List<Item> findByOwnerAccountId(String accountId);
	
    
}