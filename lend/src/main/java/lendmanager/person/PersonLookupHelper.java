package lendmanager.person;

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
		Person person = personRepository.findByAccountId(account.getId());
		return person;		
	}
	
}
