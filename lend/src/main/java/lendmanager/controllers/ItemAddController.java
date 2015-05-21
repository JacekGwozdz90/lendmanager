package lendmanager.controllers;

import lendmanager.items.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Just stub. 
 */
@Controller
@RequestMapping("/itemAdd")
public class ItemAddController {

	@Autowired 
	private ItemRepository itemRepository;
	

	@RequestMapping("/")
	public String showItemAddForm() {
		return "lendmanager/addItem";
	}
}
