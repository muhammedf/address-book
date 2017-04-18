package sunofkyuss.addressbook.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import sunofkyuss.addressbook.view.utility.Message;

@Named
@ViewScoped
public class InputView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name, surname, address, email, number;
	private List<String> numbers;

	@PostConstruct
	public void init() {
		numbers = new ArrayList<>();
	}

	public void addNumber() {
		if(number.isEmpty()){
			Message.failure("Number is empty.");
			return;
		}
		numbers.add(number);
		number = null;
	}

	public void delete(String num) {
		numbers.remove(num);
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
