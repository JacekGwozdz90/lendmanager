package lendmanager.items;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import lendmanager.person.Person;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Jacek on 2015-05-22.
 */
public class ItemDataForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

	@NotEmpty
	private String name;

	private String personId;

	private String personName;

	private String personLastName;

	@NotEmpty(message = ItemDataForm.NOT_BLANK_MESSAGE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private String lendDate;

	@NotEmpty(message = ItemDataForm.NOT_BLANK_MESSAGE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private String returnDate;

	private String remindDate;

	private String itemId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String person) {
		this.personName = person;
	}

	public String getPersonLastName() {
		return personLastName;
	}

	public void setPersonLastName(String person) {
		this.personLastName = person;
	}

	public String getLendDate() {
		return lendDate;
	}

	public void setLendDate(String lendDate) {
		this.lendDate = lendDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(String remindDate) {
		this.remindDate = remindDate;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Item createItem(ItemDataForm form, Person owner, Person person) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Item item = new Item();
		item.setName(form.getName());
		item.setOwner(owner);
		item.setPerson(person);
		
		try {
			item.setLendDate(dateFormat.parse(form.getLendDate()));
		} catch (ParseException e) {
			System.out.print(e.getMessage());
			// TODO - logging
		}
		try {
			item.setRemindDate(dateFormat.parse(form.getRemindDate()));
		} catch (ParseException e) {
			System.out.print(e.getMessage());
			// TODO - logging
		}
		try {
			item.setReturnDate(dateFormat.parse(form.getReturnDate()));
		} catch (ParseException e) {
			System.out.print(e.getMessage());
			// TODO - logging
		}

		return item;
	}
}
