package sunofkyuss.addressbook.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sunofkyuss.addressbook.dao.AddressDao;
import sunofkyuss.addressbook.dao.PersonDao;
import sunofkyuss.addressbook.dao.PhoneNumberDao;
import sunofkyuss.addressbook.model.Address;
import sunofkyuss.addressbook.model.Person;
import sunofkyuss.addressbook.model.PhoneNumber;

@Stateless
public class BookService {

	@Inject
	private PersonDao pd;

	@Inject
	private PhoneNumberDao pnd;

	@Inject
	private AddressDao ad;

	public void newRecord(String name, String surname, String address, String email, List<String> numbers) {

		Person p = new Person();
		p.setName(name);
		p.setSurname(surname);
		Address adr = new Address(address);
		ad.create(adr);
		p.setAddress(adr);
		p.setEMail(email);
		Set<PhoneNumber> pnumbers = numbers.stream()
											.map(a -> new PhoneNumber(a))
											.peek(a -> pnd.create(a))
											.collect(Collectors.toSet());
		p.setPhoneNumbers(pnumbers);
		pd.create(p);
	}

	public List<Person> listAllPersons() {
		List<Person> p = pd.listAll(0, Integer.MAX_VALUE);
		return p;
	}

	public Person getPerson(long id) {
		Person p = pd.findById(id);
		p.getPhoneNumbers().size(); //for fetch
		return p;
	}

}
