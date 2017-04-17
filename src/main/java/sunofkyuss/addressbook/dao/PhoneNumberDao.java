package sunofkyuss.addressbook.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sunofkyuss.addressbook.model.PhoneNumber;

/**
 * DAO for PhoneNumber
 */
@Stateless
public class PhoneNumberDao {
	@PersistenceContext(unitName = "AddressBook-persistence-unit")
	private EntityManager em;

	public void create(PhoneNumber entity) {
		em.persist(entity);
	}

	public void deleteById(Long id) {
		PhoneNumber entity = em.find(PhoneNumber.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public PhoneNumber findById(Long id) {
		return em.find(PhoneNumber.class, id);
	}

	public PhoneNumber update(PhoneNumber entity) {
		return em.merge(entity);
	}

	public List<PhoneNumber> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<PhoneNumber> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT p FROM PhoneNumber p LEFT JOIN FETCH p.owner ORDER BY p.id",
						PhoneNumber.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
