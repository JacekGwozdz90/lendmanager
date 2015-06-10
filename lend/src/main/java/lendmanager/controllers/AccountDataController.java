package lendmanager.controllers;

import java.security.Principal;

import lendmanager.person.Person;
import lendmanager.person.PersonLookupHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/accountData")
public class AccountDataController {

	@Autowired
	private PersonLookupHelper personLookup;
	
	@RequestMapping("/")
	public String showItemAddForm(Model model, Principal principal) {
		String email = principal.getName();
		model.addAttribute("email", email);
		Person person = personLookup.findPersonByEmail(email);
		model.addAttribute("firstName", person.getFirstName());
		model.addAttribute("lastName", person.getLastName());
		return "lendmanager/accountData";
	}
	
}
