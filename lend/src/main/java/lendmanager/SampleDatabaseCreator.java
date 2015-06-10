package lendmanager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lendmanager.items.Item;
import lendmanager.items.ItemRepository;
import lendmanager.person.Person;
import lendmanager.person.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class SampleDatabaseCreator  {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public void addData() {
		Person person1 = new Person("Jan", "Nowak");
		Person person2 = new Person("Anna", "Kowalska");
		Person person3 = new Person("Janusz", "Korwin");
		Person person4 = new Person("Patrycja", "Lisowska");
		Person person5 = new Person("Bill", "Clinton");
		List<Person> person = Lists.newArrayList(person1, person2, person3, person4, person5);
		personRepository.save(person);
		
		List<Item> items = new ArrayList<Item>();
		items.add(createItem("dlugopis", person1, person2));
		items.add(createItem("samochod", person1, person3));
		items.add(createItem("komiks", person5, person4));
		items.add(createItem("krowa", person1, person3));
		items.add(createItem("wiatrak", person3, person2));
		items.add(createItem("krzeslo", person5, person1));
		itemRepository.save(items);
	}


	private Item createItem(String name, Person owner, Person person) {
		Item item = new Item(name);
		item.setOwner(owner);
		item.setPerson(person);
		item.setLendDate(Calendar.getInstance().getTime());
		item.setReturnDate(Calendar.getInstance().getTime());
		return item;
	}
	
}
