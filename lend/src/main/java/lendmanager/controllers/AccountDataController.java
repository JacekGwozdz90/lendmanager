package lendmanager.controllers;

import java.security.Principal;

import lendmanager.account.AccountDataForm;
import lendmanager.person.Person;
import lendmanager.person.PersonLookupHelper;
import lendmanager.person.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/accountData")
public class AccountDataController {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonLookupHelper personLookup;
	
	@RequestMapping("/")
	public String showAccountData(Model model, Principal principal) {
		String email = principal.getName();
		model.addAttribute("email", email);
		Person person = personLookup.findPersonByEmail(email);
		model.addAttribute("firstName", person.getFirstName());
		model.addAttribute("lastName", person.getLastName());
		return "lendmanager/accountData";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modifyAccountData(Model model, Principal principal) {
		String email = principal.getName();
		model.addAttribute("email", email);
		Person person = personLookup.findPersonByEmail(email);
		model.addAttribute("firstName", person.getFirstName());
		model.addAttribute("lastName", person.getLastName());
		model.addAttribute(new AccountDataForm());
		return "lendmanager/accountDataModify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyAccountDataPost(@ModelAttribute AccountDataForm accountDataForm, Model model, Principal principal) {
		String firstName = accountDataForm.getFirstName();
		String lastName = accountDataForm.getLastName();
		
		String email = principal.getName();
		Person person = personLookup.findPersonByEmail(email);
		
		person.setFirstName(firstName);
		person.setLastName(lastName);
		personRepository.save(person);
		
		model.addAttribute("email", email);
		model.addAttribute("firstName", person.getFirstName());
		model.addAttribute("lastName", person.getLastName());
		return "lendmanager/accountData";
	}
}
