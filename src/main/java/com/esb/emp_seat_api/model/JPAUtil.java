package com.esb.emp_seat_api.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory;
	private static ApplicationContext ctx = new AnnotationConfigApplicationContext(JPAConfig.class);

	static {
		entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
	}

	public JPAUtil() {
		super();
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static void shutdown() {
		getEntityManagerFactory().close();
	}

	public static EntityManager getEM() {
		return JPAUtil.getEntityManagerFactory().createEntityManager();
	}
}
