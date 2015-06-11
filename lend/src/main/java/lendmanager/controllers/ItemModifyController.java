package lendmanager.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import lendmanager.items.Item;
import lendmanager.items.ItemDataForm;
import lendmanager.items.ItemRepository;
import lendmanager.person.PersonLookupHelper;
import lendmanager.person.PersonRepository;
import lendmanager.support.web.MessageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value="/",  method = RequestMethod.POST)
	public String editItemGet(@RequestBody String itemId, Model model) {
		itemId = itemId.split("=")[1];
		model.addAttribute("id", itemId);
		ItemDataForm itemDataForm = new ItemDataForm();
		Item item = itemRepository.findOne(itemId);
		itemDataForm.setItemId(itemId);
		itemDataForm.setName(item.getName());
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
		itemDataForm.setLendDate(dt1.format(item.getLendDate()));
		Date remindDate = item.getRemindDate();
		if (remindDate != null) {
			itemDataForm.setRemindDate(dt1.format(remindDate));
		}
		itemDataForm.setReturnDate(dt1.format(item.getReturnDate()));
		model.addAttribute("itemEditForm", itemDataForm);
        model.addAttribute("personList", personRepository.findAll());
		return "lendmanager/modifyItem";
	}
	
	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public String editItem(@ModelAttribute ItemDataForm itemEditForm, Errors errors, RedirectAttributes ra, Principal principal) {
		Item item = itemEditForm.createItem(itemEditForm, null, null);
		Item itemFromDB = itemRepository.findOne(itemEditForm.getItemId());
		itemFromDB.setLendDate(item.getLendDate());
		itemFromDB.setName(item.getName());
		itemFromDB.setRemindDate(item.getRemindDate());
		itemFromDB.setReturnDate(item.getReturnDate());
		itemRepository.save(itemFromDB);
		MessageHelper.addSuccessAttribute(ra, "Item updated successfully!");
		return "redirect:/";
	}
}
