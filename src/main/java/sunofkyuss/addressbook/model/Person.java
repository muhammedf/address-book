package sunofkyuss.addressbook.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import sunofkyuss.addressbook.model.PhoneNumber;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import sunofkyuss.addressbook.model.Address;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String eMail;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
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

	public Set<PhoneNumber> getPhoneNumbers() {
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(final Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (eMail != null && !eMail.trim().isEmpty())
			result += "eMail: " + eMail;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		if (surname != null && !surname.trim().isEmpty())
			result += ", surname: " + surname;
		return result;
	}

	public void newAddress() {
		this.address = new Address();
	}
}