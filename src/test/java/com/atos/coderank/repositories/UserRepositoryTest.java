package com.atos.coderank.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.atos.coderank.entities.UserEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private UserRepository ur;
	
	@Test
	public void whenFindByDas_thenReturnUser() {
		UserEntity user = new UserEntity();
		user.setDas("A123456");
		user.setName("John Smith");
		user.setEmail("john.smith@email.net");
		user.setPassword("eqalizer0");
		
		this.em.persist(user);
		this.em.flush();
		
		UserEntity found = this.ur.findByDasIgnoreCase("a123456");
		
		assertEquals(found, user);		
		
	}
}
