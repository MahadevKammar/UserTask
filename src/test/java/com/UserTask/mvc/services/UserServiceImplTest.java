package com.UserTask.mvc.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.UserTask.mvc.Entities.User;
import com.UserTask.mvc.dao.UserDAO;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql=false)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserServiceImplTest {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private TestEntityManager testEntityManager;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void userDaoSaveTest() throws Exception {
		User user = new User(1, "manu", "manu@gmail.com", "manu", "8188349834", "Bagalkot", 591102,
				getCurrentDateandTime(), 0, null, null);
		User user1 = userDao.save(user);
		assertThat(user.getUserId()).isEqualTo(user1.getUserId());
		assertNotNull(user1);
	}
	
	@Test
	public void userDaoLoginTest() throws Exception {
		User user = new User(1, "manu", "manu@gmail.com", "manu", "8188349834", "Bagalkot", 591102,
				getCurrentDateandTime(), 0, null, null);
		User savedUser=userDao.save(user);
		User user1 = userDao.findByemailAndPassword(savedUser.getEmail(),savedUser.getPassword());
		assertThat(savedUser.getUserId()).isEqualTo(user1.getUserId());
	}
	
	@Test
	public void userDaoLoadByUserEmailTest() throws Exception {
		User user = new User(1, "manu", "manu@gmail.com", "manu", "8188349834", "Bagalkot", 591102,
				getCurrentDateandTime(), 0, null, null);
		User savedUser=userDao.save(user);
		Optional<User> user1 = userDao.findByEmail(savedUser.getEmail());
		System.out.println("Saved User ID:"+savedUser.getUserId());
		System.out.println("User ID:"+user1.get().getUserId());
		assertThat(savedUser.getUserId()).isEqualTo(user1.get().getUserId());
	}

	
	
	public String getCurrentDateandTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
