package sunofkyuss.addressbook.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sunofkyuss.addressbook.model.Address;

/**
 * DAO for Address
 */
@Stateless
public class AddressDao {
	@PersistenceContext(unitName = "AddressBook-persistence-unit")
	private EntityManager em;

	public void create(Address entity) {
		em.persist(entity);
	}

	public void deleteById(Long id) {
		Address entity = em.find(Address.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Address findById(Long id) {
		return em.find(Address.class, id);
	}

	public Address update(Address entity) {
		return em.merge(entity);
	}

	public List<Address> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Address> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT a FROM Address a LEFT JOIN FETCH a.owner ORDER BY a.id",
						Address.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
