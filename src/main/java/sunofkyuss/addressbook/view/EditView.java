package sunofkyuss.addressbook.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sunofkyuss.addressbook.model.Address;
import sunofkyuss.addressbook.model.Person;
import sunofkyuss.addressbook.model.PhoneNumber;
import sunofkyuss.addressbook.service.BookService;
import sunofkyuss.addressbook.service.EditService;
import sunofkyuss.addressbook.view.utility.Message;

@Named
@ViewScoped
public class EditView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private BookService bs;

	@Inject
	private EditService es;

	@Inject
	private InputView iv;

	private Person p;

	@PostConstruct
	public void init() {
		long pid;
		String spid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pid");

		if (spid != null) {
			pid = Long.valueOf(spid);
			p = bs.getPerson(pid);
			if (p == null) {
				throw new NullPointerException("Person is null.");
			}
		} else {
			throw new IllegalArgumentException("Can't find id.");
		}

		iv.setName(p.getName());
		iv.setSurname(p.getSurname());
		iv.setAddress(p.getAddress() != null ? p.getAddress().getAddress() : null);
		iv.setEmail(p.getEMail());

	}

	public void editName() {

		if (iv.getName() != p.getName()) {
			if (es.updateName(p.getId(), iv.getName())) {
				p.setName(iv.getName());
				Message.success("Name edited.");
			} else {
				iv.setName(p.getName());
				Message.failure("Failed.");
			}
		}

	}

	public void editSurname() {

		if (iv.getSurname() != p.getSurname()) {
			if (es.updateSurname(p.getId(), iv.getSurname())) {
				p.setSurname(iv.getSurname());
				Message.success("Surname edited.");
			} else {
				iv.setSurname(p.getSurname());
				Message.failure("Failed.");
			}
		}
	}

	public void editAddress() {

		if (p.getAddress() != null && iv.getAddress() == p.getAddress().getAddress()) {
			return;
		}

		if (es.updateAddress(p.getId(), iv.getAddress())) {
			if (p.getAddress() == null) {
				p.setAddress(new Address(iv.getAddress()));
				Message.success("Address added.");
			} else {
				p.getAddress().setAddress(iv.getAddress());
				Message.success("Address edited.");
			}
		} else {
			iv.setAddress(p.getAddress() != null ? p.getAddress().getAddress() : null);
			Message.failure("Failed.");
		}

	}

	public void editEMail() {

		if (iv.getEmail() != p.getEMail()) {
			if (es.updateEMail(p.getId(), iv.getEmail())) {
				if (p.getEMail() == null) {
					Message.success("Email added.");
				} else {
					Message.success("Email edited.");
				}
				p.setEMail(iv.getEmail());
			} else {
				iv.setEmail(p.getEMail());
				Message.failure("Failed.");
			}
		}
	}

	public void addNumber() {

		PhoneNumber pnumber = es.addPhoneNumber(p.getId(), iv.getNumber());

		if (pnumber != null) {
			p.getPhoneNumbers().add(pnumber);
			System.out.println("addnumber: " + pnumber);
			System.out.println("addnumber: " + p.getPhoneNumbers());
			Message.success("Number added.");
		} else {
			Message.failure("Failed.");
		}
	}

	public void removeNumber(PhoneNumber pn) {

		if (es.deletePhoneNumber(pn.getId())) {
			p.getPhoneNumbers().remove(pn);
			Message.success("Phone number removed.");
		} else {
			Message.failure("Failed.");
		}
	}

	public Person getP() {
		return p;
	}

	public void setP(Person p) {
		this.p = p;
	}

}
