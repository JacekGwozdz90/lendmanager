package lendmanager.controllers;

import lendmanager.items.ItemAddForm;
import lendmanager.items.ItemRepository;
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
	

	@RequestMapping("/")
	public String showItemAddForm(Model model) {
		model.addAttribute(new ItemAddForm());
		System.out.println("Add Item form shown");
		return "lendmanager/addItem";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addItem(@ModelAttribute ItemAddForm itemAddForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return "ERROR";
		}
		System.out.println("Adding item");
		itemRepository.save(itemAddForm.createItem());
		System.out.print("Item saved to repository");

		MessageHelper.addSuccessAttribute(ra, "Item added");
		return "redirect:/";
	}
}
