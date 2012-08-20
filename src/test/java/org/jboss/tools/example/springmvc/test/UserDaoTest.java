package org.jboss.tools.example.springmvc.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.philihp.manhattan.domain.User;
import com.philihp.manhattan.repo.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
		"classpath:/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDaoTest {
	@Autowired
	private UserDao userDao;
	
	private int johnId; 

	@Before
	public void setUp() {
		User user = new User();
		user.setName("John Smith");
		user.setEmail("john@mail.com");
		userDao.register(user);
		johnId = user.getId();
	}
	
	@Test
	public void testFindById() {
		User user = userDao.findById(johnId);

		Assert.assertEquals("John Smith", user.getName());
		Assert.assertEquals("john@mail.com", user.getEmail());
		return;
	}

	@Test
	public void testFindByEmail() {
		User user = userDao.findByEmail("john@mail.com");

		Assert.assertEquals("John Smith", user.getName());
		Assert.assertEquals("john@mail.com", user.getEmail());
		return;
	}

	@Test
	public void testRegister() {
		User user = new User();
		user.setEmail("jane.doe@mailinator.com");
		user.setName("Jane Doe");

		userDao.register(user);
		Integer id = user.getId();
		Assert.assertNotNull(id);

		Assert.assertEquals(2, userDao.findAllOrderedByName().size());
		User newUser = userDao.findById(id);

		Assert.assertEquals("Jane Doe", newUser.getName());
		Assert.assertEquals("jane.doe@mailinator.com", newUser.getEmail());
		return;
	}

	@Test
	public void testFindAllOrderedByName() {
		User user = new User();
		user.setEmail("jane.doe@mailinator.com");
		user.setName("Jane Doe");
		userDao.register(user);

		List<User> users = userDao.findAllOrderedByName();
		
		Assert.assertEquals(2, users.size());
		User newUser = users.get(0);

		Assert.assertEquals("Jane Doe", newUser.getName());
		Assert.assertEquals("jane.doe@mailinator.com", newUser.getEmail());
		return;
	}
}
