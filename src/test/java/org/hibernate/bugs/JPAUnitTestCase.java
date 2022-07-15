package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPAUnitTestCase {
	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	@Test
	public void testQueryInTransaction() throws Exception {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		ObjectItem oi = new ObjectItem("oiname");
		oi.addComment().addContent("comment");
		em.persist(oi);
		trans.commit();
		em.close();

		em = entityManagerFactory.createEntityManager();
		trans = em.getTransaction();
		trans.begin();
		oi = new ObjectItem("oiname");
		oi.addComment().addContent("comment");
		em.persist(oi);
		em.createQuery("from ObjectItem", ObjectItem.class).getResultList().forEach(objItem -> objItem.getComments().forEach(comment -> comment.getContents().forEach(content -> System.out.println(content.getText()))));
		trans.commit();
		em.close();
	}
}
