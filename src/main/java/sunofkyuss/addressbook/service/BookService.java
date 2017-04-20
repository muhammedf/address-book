package sunofkyuss.addressbook.service;

import java.util.List;
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

	public boolean newRecord(String name, String surname, String address, String email, List<String> numbers) {
		try {
			Person p = new Person();

			p.setName(name);
			p.setSurname(surname);

			if (!address.isEmpty()) {
				Address adr = new Address(address);
				ad.create(adr);
				p.setAddress(adr);
			}

			p.setEMail(email.isEmpty() ? null : email);

			numbers.stream().map(a -> new PhoneNumber(a)).peek(a -> a.setOwner(p)).peek(a -> pnd.create(a))
					.collect(Collectors.toSet());

			pd.create(p);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public List<Person> listAllPersons() {

		List<Person> p = null;

		try {
			p = pd.listAll(0, Integer.MAX_VALUE);
		} catch (Exception e) {
		}

		return p;
	}

	public boolean removePerson(long id) {
		try {
			pd.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
