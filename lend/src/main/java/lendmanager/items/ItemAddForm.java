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


    public Item createItem() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.print("Creating item");
        Item item = new Item();
        System.out.println("get Name : " + getName());
        item.setName(getName());
        item.setPerson(new Person(getPersonName(), getPersonLastName()));
        item.setOwner(new Person("owner", "owner"));
        try {
            item.setLendDate(dateFormat.parse(getLendDate()));
            item.setRemindDate(dateFormat.parse(getRemindDate()));
            item.setReturnDate(dateFormat.parse(getReturnDate()));

        } catch (ParseException e) {
            System.out.print(e.getMessage());
        }
        System.out.print("Item created");

        return item;
    }
}
