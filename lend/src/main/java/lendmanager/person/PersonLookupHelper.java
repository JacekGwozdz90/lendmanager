package lendmanager.person;

import java.util.List;

import lendmanager.account.Account;
import lendmanager.account.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PersonLookupHelper {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person findPersonByEmail(String email) {
		Account account = accountRepository.findByEmail(email);
		if (account == null) {
			return null;
		}
		Person person = personRepository.findByAccountId(account.getId());
		return person;		
	}

	public Person findByNameOrCreate(String personName, String personLastName) {
		List<Person> findByFirstNameAndLastName = personRepository.findByFirstNameAndLastName(personName, personLastName);
		if (findByFirstNameAndLastName.size() == 0) {
			Person person = new Person(personName, personLastName);
			personRepository.save(person);
			return person;
		} else {
			return findByFirstNameAndLastName.get(0); 
		}
	}
	
}
