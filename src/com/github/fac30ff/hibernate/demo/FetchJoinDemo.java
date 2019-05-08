package com.github.fac30ff.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.github.fac30ff.hibernate.demo.entity.Course;
import com.github.fac30ff.hibernate.demo.entity.Instructor;
import com.github.fac30ff.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//begin  a transaction
			session.beginTransaction();
			//Hibernate query with HQL 
			//get the instructor from db
			int id = 1;
			Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id =:instructorId", Instructor.class);
			//set parameter on query
			query.setParameter("instructorId", id);
			//execute query and get instructor
			Instructor instructor = query.getSingleResult();
			System.out.println("faceoff: Instructor: " + instructor);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("faceoff: Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
