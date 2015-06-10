package lendmanager.controllers;

import java.security.Principal;

import lendmanager.items.ItemAddForm;
import lendmanager.items.ItemRepository;
import lendmanager.person.Person;
import lendmanager.person.PersonLookupHelper;
import lendmanager.support.web.MessageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Just stub. 
 */
@Controller
@RequestMapping("/itemAdd")
public class ItemAddController {

	@Autowired 
	private ItemRepository itemRepository;

	@Autowired
	private PersonLookupHelper personLookup;
	
	@RequestMapping("/")
	public String showItemAddForm(Model model) {
		model.addAttribute(new ItemAddForm());
		System.out.println("Add Item form shown");
		return "lendmanager/addItem";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addItem(@ModelAttribute ItemAddForm itemAddForm, Errors errors, RedirectAttributes ra, Principal principal) {
		if (errors.hasErrors()) {
			return "ERROR";
		}
		
		// TODO - change borrowing person creation to lookup / creation
		Person person = new Person(itemAddForm.getPersonName(), itemAddForm.getPersonLastName());
		Person owner = personLookup.findPersonByEmail(principal.getName()); 
	
		itemRepository.save(itemAddForm.createItem(itemAddForm, owner, person));

		MessageHelper.addSuccessAttribute(ra, "Item added");
		return "redirect:/";
	}
}
