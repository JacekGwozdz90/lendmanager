package lendmanager.controllers;

import java.security.Principal;

import lendmanager.account.Account;
import lendmanager.account.AccountRepository;
import lendmanager.items.ItemRepository;
import lendmanager.person.Person;
import lendmanager.person.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jacek on 2015-05-22.
 */
@Controller
@RequestMapping("/itemList")
public class ItemListController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @RequestMapping("/")
    public String showItemAddForm(Model model, Principal principal) {
    	Account account = accountRepository.findByEmail(principal.getName());
    	String accountId = account.getId();
    	Person owner = personRepository.findByAccountId(accountId);
        model.addAttribute("itemList", itemRepository.findByOwnerId(owner.getId()));
        return "lendmanager/listItem";
    }


}
