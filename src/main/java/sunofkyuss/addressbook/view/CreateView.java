package sunofkyuss.addressbook.view;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sunofkyuss.addressbook.service.BookService;
import sunofkyuss.addressbook.view.utility.Message;

@Named
@RequestScoped
public class CreateView {

	@Inject
	private InputView iv;

	@Inject
	private BookService bs;

	@PostConstruct
	public void init() {
	}

	public void saveRecord() {

		if (bs.newRecord(iv.getName(), iv.getSurname(), iv.getAddress(), iv.getEmail(), iv.getNumbers())) {
			Message.success("Record saved.");
			iv.setName(null);
			iv.setSurname(null);
			iv.setAddress(null);
			iv.setEmail(null);
			iv.setNumber(null);
			iv.setNumbers(new ArrayList<>());
		} else {
			Message.failure("Failed.");
		}
	}
}
