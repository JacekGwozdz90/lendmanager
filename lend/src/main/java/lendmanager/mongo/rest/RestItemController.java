package lendmanager.mongo.rest;

import java.util.Calendar;
import java.util.List;

import lendmanager.items.Item;
import lendmanager.items.ItemRepository;
import lendmanager.person.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;


@RestController
@RequestMapping("/rest/items")
public class RestItemController {

	@Autowired
	private ItemRepository itemRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	@RequestMapping(value = "/addSampleData", method = RequestMethod.GET)
	public String createTestItems() {
		List<Item> entites = Lists.newArrayList();
		Item item = new Item("kilof");
		item.setLendDate(Calendar.getInstance().getTime());
		item.setPerson(new Person("to nie", "ja"));
		entites.add(item);
		entites.add(new Item("dlugopis"));
		entites.add(new Item("komputer"));
		itemRepository.save(entites);
		return "Items saved";
	}
}
