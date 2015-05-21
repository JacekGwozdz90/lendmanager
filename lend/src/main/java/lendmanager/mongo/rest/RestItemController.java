package lendmanager.mongo.rest;

import java.util.List;

import lendmanager.mongo.Item;
import lendmanager.mongo.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/items")
public class RestItemController {

	@Autowired
	private ItemRepository itemRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
}
