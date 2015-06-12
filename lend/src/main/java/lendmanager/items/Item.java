package lendmanager.items;

import java.util.Date;

import lendmanager.person.Person;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Item {

    @Id
    private String id;
    private String name;
    
    @DBRef
    private Person owner;
    @DBRef
    private Person person;
    
    private Date lendDate;
    private Date returnDate;
    private Date remindDate;
    
    public Item() {}

    public Item(String name) {
        this.name = name;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getLendDate() {
		return lendDate;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(Date remindDate) {
		this.remindDate = remindDate;
	}

	@Override
	public String toString() {
		return "Item [" + (id != null ? "id=" + id + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (person != null ? "person=" + person + ", " : "")
				+ (lendDate != null ? "lendDate=" + lendDate + ", " : "")
				+ (returnDate != null ? "returnDate=" + returnDate + ", " : "")
				+ (remindDate != null ? "remindDate=" + remindDate : "") + "]";
	}
    
}