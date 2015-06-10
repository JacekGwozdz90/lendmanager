package lendmanager.mongo.rest;

import java.util.List;

import lendmanager.person.Person;
import lendmanager.person.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/person")
public class RestPersonController {

	@Autowired
	private PersonRepository personRepository;
	
	
	/**
	 * Returns list of all person found in database.
	 * 
	 * @return list of found person
	 */
	@RequestMapping(value = "/query/all", method = RequestMethod.GET, produces = "application/json")
	public List<Person> getAllPerson() {
		return personRepository.findAll();
	} 
	
	/**
	 * Removes all person - developer use only!
	 */
	@RequestMapping(value = "/delete/all", method = RequestMethod.GET, produces = "application/json")
	public String deleteAllPerson() {
		personRepository.deleteAll();
		return "Deleted.";
	} 
	
}
