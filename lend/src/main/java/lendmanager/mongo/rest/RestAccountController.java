package lendmanager.mongo.rest;

import java.util.List;

import lendmanager.account.Account;
import lendmanager.account.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/accounts")
public class RestAccountController {

	@Autowired
	private AccountRepository accountRepository;

	/**
	 * Returns list of all accounts found in database.
	 * 
	 * @return list of found accounts
	 */
	@RequestMapping(value = "/query/all", method = RequestMethod.GET, produces = "application/json")
	public List<Account> getAllItems() {
		return accountRepository.findAll();
	}
	
	
	/**
	 * Removes all accounts - developer use only!
	 */
	@RequestMapping(value = "/delete/all")
	public String deleteAllAccounts() {
		accountRepository.deleteAll();
		return "Deleted.";
	}
	
}
