package lendmanager.controllers;

import java.security.Principal;

import lendmanager.account.AccountRepository;
import lendmanager.items.Item;
import lendmanager.items.ItemRepository;

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

    @RequestMapping("/")
    public String showItemAddForm(Model model, Principal principal) {
        model.addAttribute("itemList", itemRepository.findAll());
        System.out.println("Add  Item list to show");
        return "lendmanager/listItem";
    }


}
