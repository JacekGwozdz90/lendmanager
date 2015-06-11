package lendmanager.notifications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lendmanager.account.AccountRepository;
import lendmanager.items.Item;
import lendmanager.items.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.thymeleaf.util.DateUtils;

public class EmailScheduler {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Scheduled(fixedRate = 86400000)
	public void scheduleMails(){
		for(Item item :itemRepository.findAll()){
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			if(DateUtils.createToday().equals(item.getRemindDate())){
				new EmailSendTask(accountRepository,item);
			}
		}
	}
}
