package lendmanager.items;

import lendmanager.person.Person;

import org.hibernate.validator.constraints.NotBlank;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Jacek on 2015-05-22.
 */

public class ItemAddForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

	@NotBlank(message = ItemAddForm.NOT_BLANK_MESSAGE)
	private String name;

	@NotBlank(message = ItemAddForm.NOT_BLANK_MESSAGE)
	private String personName;

	@NotBlank(message = ItemAddForm.NOT_BLANK_MESSAGE)
	private String personLastName;

	@NotBlank(message = ItemAddForm.NOT_BLANK_MESSAGE)
	private String lendDate;

	@NotBlank(message = ItemAddForm.NOT_BLANK_MESSAGE)
	private String returnDate;

	@NotBlank(message = ItemAddForm.NOT_BLANK_MESSAGE)
	private String remindDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Item createItem(ItemAddForm form, Person owner, Person person) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Item item = new Item();
		item.setName(form.getName());
		item.setOwner(owner);
		item.setPerson(person);
		try {
			item.setLendDate(dateFormat.parse(form.getLendDate()));
			item.setRemindDate(dateFormat.parse(form.getRemindDate()));
			item.setReturnDate(dateFormat.parse(form.getReturnDate()));
		} catch (ParseException e) {
			System.out.print(e.getMessage());
			// TODO - logging
		}
		return item;
	}
}
