package sunofkyuss.addressbook.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sunofkyuss.addressbook.dao.PersonDao;
import sunofkyuss.addressbook.dao.PhoneNumberDao;
import sunofkyuss.addressbook.model.Address;
import sunofkyuss.addressbook.model.Person;
import sunofkyuss.addressbook.model.PhoneNumber;

@Stateless
public class EditService {

	@Inject
	private PersonDao pd;

	@Inject
	private PhoneNumberDao pnd;

	public boolean updateName(long pid, String name) {

		try {
			Person p = pd.findById(pid);
			p.setName(name);
			pd.update(p);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean updateSurname(long pid, String surname) {

		try {
			Person p = pd.findById(pid);
			p.setSurname(surname);
			pd.update(p);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean updateAddress(long pid, String address) {

		try {
			Person p = pd.findById(pid);
			if (p.getAddress() == null) {
				p.setAddress(new Address(address));
			} else {
				p.getAddress().setAddress(address);
			}
			pd.update(p);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean updateEMail(long pid, String email) {

		try {
			Person p = pd.findById(pid);
			p.setEMail(email);
			pd.update(p);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public PhoneNumber addPhoneNumber(long pid, String number) {

		PhoneNumber pnumber=null;
		
		try {
			pnumber = new PhoneNumber(number);
			Person p = pd.findById(pid);
			pnumber.setOwner(p);
			pnd.create(pnumber);
		} catch (Exception e) {
			return null;
		}

		return pnumber;
	}

	public boolean deletePhoneNumber(long pnid) {

		try {
			pnd.deleteById(pnid);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
