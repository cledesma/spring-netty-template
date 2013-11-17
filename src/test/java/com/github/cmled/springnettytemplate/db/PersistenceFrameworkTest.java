package com.github.cmled.springnettytemplate.db;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import com.github.cmled.springnettytemplate.dao.GenericDao;
import com.github.cmled.springnettytemplate.model.User;

/*
 * Test for the persistence-related framework
 * 
 * Uses User entity for testing
 * 
 * Files to keep track of:
 * 
 * 1. test-data.xml (src/test/resources) --> used by EntityManaager tests
 * 2. import-users.sql (src/main/resources/sql) --> used by GenericDao tests
 */
public class PersistenceFrameworkTest extends AbstractDbUnitJpaTest {

	/*
	 * EntityManager Tests
	 * 
	 * This is a test for code using Entity Manager directly (instead of
	 * GenericDao)
	 * 
	 * Records inserted in the tests are removed by the framework after
	 * completion of PersistenceFrameworkTest
	 */
	@Test
	public void testFindUserUsingEm() {

		User user = entityManager.find(User.class, 1l);
		Assert.assertNotNull(user);
		Assert.assertEquals("Krista", user.getFirstName());
		Assert.assertEquals("Cancel", user.getLastName());
		LocalDate actualBirthday = new LocalDate(user.getBirthday());
		Assert.assertEquals(7, actualBirthday.getMonthOfYear());
		Assert.assertEquals(27, actualBirthday.getDayOfMonth());
		Assert.assertEquals(1999, actualBirthday.getYear());
	}

	@Test
	public void testInsertUserUsingEm() {

		User newUser = new User();
		newUser.setFirstName("Carlo Miguel");
		newUser.setLastName("Ledesma");
		LocalDate tmpBirthday = new LocalDate(1991, 1, 8);
		newUser.setBirthday(tmpBirthday.toDate());

		entityManager.getTransaction().begin();
		entityManager.persist(newUser);
		long id = newUser.getId();
		entityManager.getTransaction().commit();

		User user = entityManager.find(User.class, id);
		Assert.assertNotNull(user);
		Assert.assertEquals("Carlo Miguel", user.getFirstName());
		Assert.assertEquals("Ledesma", user.getLastName());
		LocalDate actualBirthday = new LocalDate(user.getBirthday());
		Assert.assertEquals(1, actualBirthday.getMonthOfYear());
		Assert.assertEquals(8, actualBirthday.getDayOfMonth());
		Assert.assertEquals(1991, actualBirthday.getYear());
	}

	@Test
	public void testFindAllUsingEm() {

		@SuppressWarnings("unchecked")
		List<User> allUsers = entityManager.createQuery("from User")
				.getResultList();
		Assert.assertEquals(2, allUsers.size());
	}

	@Test
	public void testDeleteUserUsingEm() {

		@SuppressWarnings("unchecked")
		List<User> allUsersBeforeDelete = entityManager
				.createQuery("from User").getResultList();
		Assert.assertEquals(2, allUsersBeforeDelete.size()); // should have
																// initial 2
																// entries

		entityManager.getTransaction().begin();
		entityManager.remove(allUsersBeforeDelete.get(0)); // remove first entry
		entityManager.getTransaction().commit();

		@SuppressWarnings("unchecked")
		List<User> allUsersAfterDelete = entityManager.createQuery("from User")
				.getResultList();
		Assert.assertEquals(1, allUsersAfterDelete.size()); // should have 1
															// entry
	}

	/*
	 * GenericDao Tests
	 * 
	 * Note: Records persisted using direct EntityManager above are not visible
	 * to the GenericDao tests below
	 * 
	 * Records persisted below are removed after test completion
	 */

	@Test
	public void testInsertUserGenericDao() {
		GenericDao<User> dao = new GenericDao<User>(User.class);
		List<User> users = dao.getAll();
		Assert.assertEquals(1, users.size());

		User newUser = new User();
		newUser.setFirstName("Kristy Mich");
		newUser.setLastName("Dela Cruz");
		LocalDate tmpBirthday = new LocalDate(1986, 5, 30);
		newUser.setBirthday(tmpBirthday.toDate());

		dao.merge(newUser);
		Assert.assertEquals(2, dao.getAll().size());
	}

	@Test
	public void testInsertUserGenericDao2() {
		GenericDao<User> dao = new GenericDao<User>(User.class);
		List<User> users = dao.getAll();
		Assert.assertEquals(2, users.size());

		User newUser = new User();
		newUser.setFirstName("John Doe");
		newUser.setLastName("Santos");
		LocalDate tmpBirthday = new LocalDate(1979, 6, 29);
		newUser.setBirthday(tmpBirthday.toDate());

		dao.merge(newUser);
		Assert.assertEquals(3, dao.getAll().size());
	}

	@Test
	public void testCustomQuery() {
		GenericDao<User> dao = new GenericDao<User>(User.class);
		String q = "from User where last_name = 'Ramirez'";
		List<User> users = dao.doCustomQuery(q);
		Assert.assertEquals(1, users.size());
	}
}
