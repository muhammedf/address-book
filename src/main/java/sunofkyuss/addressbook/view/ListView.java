package sunofkyuss.addressbook.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sunofkyuss.addressbook.model.Person;
import sunofkyuss.addressbook.service.BookService;
import sunofkyuss.addressbook.view.utility.Message;

@Named
@ViewScoped
public class ListView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private BookService bs;

	private List<Person> persons;

	@PostConstruct
	public void init() {
		persons = bs.listAllPersons();
	}

	public void removePerson(Person p) {
		
		if (bs.removePerson(p.getId())) {
			persons.remove(p);
			Message.success("Record removed.");
		} else {
			Message.failure("Failed.");
		}
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
