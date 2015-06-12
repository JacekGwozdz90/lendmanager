package lendmanager.notifications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
	
	@Scheduled(fixedRate = 8640000)
	public void scheduleMails(){
		System.out.println("Scheduling mails!");
		for(Item item :itemRepository.findAll()){
			LocalDate today = LocalDate.now();
			Date remindDay = item.getRemindDate();
			Instant instant = Instant.ofEpochMilli(remindDay.getTime());
			LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
			if(today.equals(res)){
				System.out.println("GOGO!" + item);
				new EmailSendTask(accountRepository,item).run();;
			}
		}
	}
}
