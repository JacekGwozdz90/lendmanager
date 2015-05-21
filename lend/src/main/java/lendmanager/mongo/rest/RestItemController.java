package lendmanager.mongo.rest;

import java.util.Calendar;
import java.util.List;

import lendmanager.items.Item;
import lendmanager.items.ItemRepository;
import lendmanager.person.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
@RequestMapping("/rest/items")
public class RestItemController {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * Returns list of all items found in database.
	 * 
	 * @return list of found items
	 */
	@RequestMapping(value = "/query/all", method = RequestMethod.GET, produces = "application/json")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	/**
	 * Return list of items with given <code>name</code> attribute.
	 * 
	 * @param name
	 *            name of item
	 * @return list of items with requested name
	 */
	@RequestMapping(value = "/query/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Item> getItemsByName(@PathVariable("name") String name) {
		return itemRepository.findByName(name);
	}

	/**
	 * Returns list of items lent to person of given id. Optional QueryParam
	 * <code>useFacebook</code> specifies whether simple or facebook id should
	 * be used in query. The default configuration searched for simple id.
	 * 
	 * @param personId - simple or facebook id of a person
	 * @param useFacebook - parameted specifying id type
	 * @return list of items lent to person of given id.
	 */
	@RequestMapping(value = "/query/lentTo/{personId}", method = RequestMethod.GET, produces = "application/json")
	public List<Item> getItemsByPersonId(
			@PathVariable("personId") Integer personId,
			@RequestParam(required = false) boolean useFacebook) {
		if (useFacebook) {
			return itemRepository.findByPersonFacebookId(personId);
		} else {
			return itemRepository.findByPersonId(personId);
		}
	}

	/**
	 * Returns list of items lent to person of given first name and last name.
	 * Both arguments are required.
	 * @param firstName - first name of person
	 * @param lastName - last name of person
	 * @return list of items lent to person of given first name and last name
	 */
	@RequestMapping(value = "/query/lentTo/firstName/{fName}/lastName/{lName}", method = RequestMethod.GET, produces = "application/json")
	public List<Item> getItemsByPersonName(
			@PathVariable("fName") String firstName,
			@PathVariable("lName") String lastName) {
		return itemRepository.findByPersonFirstNameAndPersonLastName(firstName,
				lastName);
	}

	
	/**
	 * Development method.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addSampleData", method = RequestMethod.GET)
	public String createTestItems() {
		List<Item> entites = Lists.newArrayList();
		Item item = new Item("kilof");
		item.setLendDate(Calendar.getInstance().getTime());
		Person person = new Person("anna", "kowalska");
		person.setId(1);
		person.setFacebookId(2);
		item.setPerson(person);

		entites.add(item);
		entites.add(new Item("dlugopis"));
		entites.add(new Item("komputer"));
		itemRepository.save(entites);
		return "Items saved";
	}
}
