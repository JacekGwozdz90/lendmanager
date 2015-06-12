package lendmanager.mongo.rest;

import java.util.Calendar;
import java.util.List;

import lendmanager.error.ItemNotFoundException;
import lendmanager.items.Item;
import lendmanager.items.ItemRepository;
import lendmanager.person.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	 * Removes all items - developer use only!.
	 */
	@RequestMapping(value = "/delete/all", method = RequestMethod.GET, produces = "application/json")
	public String deleteAllItems() {
		itemRepository.deleteAll();
		return "Deleted.";
	} 

	/**
	 * Returns list of all items owned by specified person found in database.
	 * Optional QueryParam <code>useFacebook</code> specifies whether simple or
	 * facebook id should be used in query. The default configuration searches
	 * for simple id.
	 * 
	 * @param ownerId
	 *            simple or facebook id of an owner
	 * @param useFacebook
	 *            parameted specifying id type
	 * @return list of found items owned by
	 */
	@RequestMapping(value = "/query/ownedBy/{ownerId}", method = RequestMethod.GET, produces = "application/json")
	public List<Item> getAllItemsOwnedBy(@PathVariable("ownerId") String ownerId,
			@RequestParam(required = false) boolean useFacebook) {
		if (useFacebook) {
			return itemRepository.findByOwnerFacebookId(ownerId);
		} else {
			return itemRepository.findByOwnerId(ownerId);
		}
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
	 * be used in query. The default configuration searches for simple id.
	 * 
	 * @param personId
	 *            simple or facebook id of a person
	 * @param useFacebook
	 *            parameted specifying id type
	 * @return list of items lent to person of given id.
	 */
	@RequestMapping(value = "/query/lentTo/{personId}", method = RequestMethod.GET, produces = "application/json")
	public List<Item> getItemsByPersonId(@PathVariable("personId") Integer personId,
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
	 * 
	 * @param firstName
	 *            first name of person
	 * @param lastName
	 *            last name of person
	 * @return list of items lent to person of given first name and last name
	 */
	@RequestMapping(value = "/query/lentTo/firstName/{fName}/lastName/{lName}", method = RequestMethod.GET, produces = "application/json")
	public List<Item> getItemsByPersonName(@PathVariable("fName") String firstName,
			@PathVariable("lName") String lastName) {
		return itemRepository.findByPersonFirstNameAndPersonLastName(firstName, lastName);
	}

	/**
	 * Adds item to database. Item should be sent in POST body as JSON object.
	 * 
	 * @param item
	 *            item to be saved, sent as request body
	 */
	@RequestMapping(value = "/data/add", method = RequestMethod.POST, consumes = "application/json")
	public void addItem(@RequestBody Item item) {
		if (item != null) {
			itemRepository.save(item);
		}
	}

	/**
	 * Updates item with specified id, using values send in POST body. If
	 * request param saveNulls is present and it's value is "true", all nulls
	 * present in JSON are persisted in database. Otherwise, only attributes
	 * that are specified in JSON are updated.
	 * 
	 * 
	 * @param item
	 *            item to be updated, sent as request body
	 * @param itemId
	 *            id of item to be updated
	 * @param saveNulls
	 *            specifies, whether nulls in POST Json should be persisted or
	 *            not
	 */
	@RequestMapping(value = "/data/update/{itemId}", method = RequestMethod.POST, consumes = "application/json")
	public void updateItem(@RequestBody Item item, @PathVariable("itemId") String itemId,
			@RequestParam(value = "saveNulls", required = false, defaultValue = "false") boolean saveNulls) {
		
		Item itemToUpdate = itemRepository.findOne(itemId);

		if (itemToUpdate == null) {
			throw new ItemNotFoundException("Update failed - not existing item: "+itemId);
		}
		
		if (saveNulls) {
			itemToUpdate.setLendDate(item.getLendDate());
			itemToUpdate.setName(item.getName());
			itemToUpdate.setOwner(item.getOwner());
			itemToUpdate.setPerson(item.getPerson());
			itemToUpdate.setRemindDate(item.getRemindDate());
			itemToUpdate.setReturnDate(item.getReturnDate());
		} else {
			if (item.getLendDate() != null)
				itemToUpdate.setLendDate(item.getLendDate());
			if (item.getName() != null)
				itemToUpdate.setName(item.getName());
			if (item.getOwner() != null)
				itemToUpdate.setOwner(item.getOwner());
			if (item.getPerson() != null)
				itemToUpdate.setPerson(item.getPerson());
			if (item.getRemindDate() != null)
				itemToUpdate.setRemindDate(item.getRemindDate());
			if (item.getReturnDate() != null)
				itemToUpdate.setReturnDate(item.getReturnDate());
		}
		itemToUpdate.setId(itemId);
		itemRepository.save(itemToUpdate);
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
		person.setId("1");
		person.setFacebookId("2");
		item.setPerson(person);

		entites.add(item);
		entites.add(new Item("dlugopis"));
		entites.add(new Item("komputer"));
		itemRepository.save(entites);
		return "Items saved";
	}
}
