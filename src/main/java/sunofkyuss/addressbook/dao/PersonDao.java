package sunofkyuss.addressbook.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sunofkyuss.addressbook.model.Person;

/**
 * DAO for Person
 */
@Stateless
public class PersonDao {
	@PersistenceContext(unitName = "AddressBook-persistence-unit")
	private EntityManager em;

	public void create(Person entity) {
		em.persist(entity);
	}

	public void deleteById(Long id) {
		Person entity = em.find(Person.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Person findById(Long id) {
		return em.find(Person.class, id);
	}

	public Person update(Person entity) {
		return em.merge(entity);
	}

	public List<Person> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Person> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.phoneNumbers LEFT JOIN FETCH p.address ORDER BY p.id",
						Person.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
