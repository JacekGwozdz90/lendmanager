package lendmanager.controllers;

import java.security.Principal;

import lendmanager.items.Item;
import lendmanager.items.ItemDataForm;
import lendmanager.items.ItemRepository;
import lendmanager.person.Person;
import lendmanager.person.PersonLookupHelper;
import lendmanager.person.PersonRepository;
import lendmanager.support.web.MessageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/editItem")
public class ItemModifyController {
	
	@Autowired 
	private ItemRepository itemRepository;

	@Autowired 
	private PersonRepository personRepository;
	
	@Autowired
	private PersonLookupHelper personLookup;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String editItemGet() {
		return "lendmanager/listItem";
	}
	
	@RequestMapping(value="/")
	public String editItemGet( Model model) {
		model.addAttribute("id", ""); // @ModelAttribute String itemId,
		model.addAttribute("itemEditForm", new ItemDataForm());
		return "lendmanager/modifyItem";
	}
	
	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public String editItem(@ModelAttribute ItemDataForm itemEditForm, Errors errors, RedirectAttributes ra, Principal principal) {
		throw new UnsupportedOperationException();
//		if (errors.hasErrors()) {
//			return "ERROR";
//		}
//		
//		Person person;
//		String personId = itemEditForm.getPersonId();
//		if (personId != null && !personId.trim().isEmpty()) {
//			person = personRepository.findOne(personId);
//		} else {
//			person = personLookup.findByNameOrCreate(itemEditForm.getPersonName(), itemEditForm.getPersonLastName());
//		}
//		
//		Item item = null;
//		itemRepository.save(item);
//
//		MessageHelper.addSuccessAttribute(ra, "Item updated successfully!");
//		return "redirect:/";
	}
}
