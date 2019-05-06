package com.github.fac30ff.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.fac30ff.hibernate.demo.entity.Course;
import com.github.fac30ff.hibernate.demo.entity.Instructor;
import com.github.fac30ff.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			//get the instructor from db
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);
			//create some courses
			Course course1 = new Course("Air Guitar - the Ultimate Guide");
			Course course2 = new Course("The Pinball Masterclass");
			//add courses to instructor
			instructor.add(course1);
			instructor.add(course2);
			//save the courses
			session.save(course1);
			session.save(course2);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
