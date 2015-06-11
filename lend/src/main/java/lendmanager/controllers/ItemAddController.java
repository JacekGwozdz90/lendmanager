package lendmanager.controllers;

import java.security.Principal;

import lendmanager.account.AccountRepository;
import lendmanager.items.Item;
import lendmanager.items.ItemDataForm;
import lendmanager.items.ItemRepository;
import lendmanager.notifications.EmailSendTask;
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
@RequestMapping("/itemAdd")
public class ItemAddController {

	@Autowired 
	private ItemRepository itemRepository;

	@Autowired 
	private PersonRepository personRepository;
	
	@Autowired
	private PersonLookupHelper personLookup;
	
	
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String showItemAddForm(Model model) {
		model.addAttribute("itemAddForm", new ItemDataForm());
		model.addAttribute("personList", personRepository.findAll());
		return "lendmanager/addItem";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addItem(@ModelAttribute ItemDataForm itemAddForm, Errors errors, RedirectAttributes ra, Principal principal) {
		
		if (errors.hasErrors()) {
			return "ERROR";
		}
		
		Person person;
		String personId = itemAddForm.getPersonId();
		
		if (personId != null && !personId.trim().isEmpty()) {
			person = personRepository.findOne(personId);
		} else {
			person = personLookup.findByNameOrCreate(itemAddForm.getPersonName(), itemAddForm.getPersonLastName());
		}
		
		Person owner = personLookup.findPersonByEmail(principal.getName()); 
		Item item = itemAddForm.createItem(itemAddForm, owner, person);
		
		itemRepository.save(itemAddForm.createItem(itemAddForm, owner, person));
		EmailSendTask email = new EmailSendTask(accountRepository,item);
		email.run();
		MessageHelper.addSuccessAttribute(ra, "Item added successfully!");
		return "redirect:/";
	}
}
