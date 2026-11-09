package com.spring.hibernate_demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

/**
 * Main entry point for the Spring Boot Hibernate Demo application.
 * Demonstrates CRUD operations using JPA and a custom DAO implementation.
 */
@SpringBootApplication
public class HibernateDemoApplication {

	/**
	 * Main method to launch the Spring Boot application context.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	/**
	 * Defines a {@link CommandLineRunner} bean executed automatically after the
	 * application context is loaded.
	 *
	 * @param studentDao the {@link StudentDao} bean injected by Spring
	 * @return a Lambda expression executing database demo operations
	 */
	// @Bean
	// CommandLineRunner commandLineRunner(StudentDao studentDao) {
	// return runner -> {
	// //createStudent(studentDao);
	// // readStudent(studentDao);
	// queryStudents(studentDao);
	// // queryStudentLastName(studentDao);
	// // updateStudent(studentDao);
	// // deleteStudent(studentDao);
	// // deleteAllStudent(studentDao);

	// };
	// }

	@Bean
	CommandLineRunner commandLineRunner(AppDao app) {
		return runner -> {
			// createInstructor(app);
			// findInstructor(app);
			// deleteInstructor(app);
			// findInstructorDetail(app);
			// deleteInstructorDetail(app);
			createInstructorWithCourses(app);
		};
	}

	private void createInstructor(AppDao app) {
		// create the instructor and details
		Instructor ins = new Instructor("Armin",
				"Arlert",
				"armin.arlert@example.com");
		InstructorDetail deets = new InstructorDetail("Battle IQ",
				"Making huge revelations");

		// associate the objects
		ins.setInstructorDeets(deets);

		// save the instructor
		// this will also save the details object
		// because of the CascadeType.ALL
		System.out.println("Information saved!");
		app.save(ins);
	}

	public void findInstructor(AppDao app) {
		int id = 1;
		System.out.println("Find id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Instructor details: " + ins.getInstructorDeets());
	}

	public void deleteInstructor(AppDao app) {
		int id = 2;
		System.out.println("Delete id: " + id);

		app.deleteInstructorId(id);
		System.out.println("Deleted successfully!");
	}

	public void findInstructorDetail(AppDao app) {
		// get the instructor detail object
		int id = 1;
		System.out.println("Find id: " + id);

		InstructorDetail inDeets = app.findInstructorDetailById(id);

		// print the instructor detail
		System.out.println("Details: " + inDeets);

		// print the associated instructor
		System.out.println("Instructor: " + inDeets.getInstructor());
	}

	private void deleteInstructorDetail(AppDao app) {
		int id = 3;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetail(id);
		System.out.println("Deleted successfully!");
	}

	private void createInstructorWithCourses(AppDao app) {
		// create the instructor and details
		Instructor ins = new Instructor("Pieck",
				"Finger",
				"pieck.finger@example.com");

		InstructorDetail deets = new InstructorDetail("Kagaku","Saving humanity");

		// associate the objects
		ins.setInstructorDeets(deets);

		// create courses
		Course course1 = new Course("Physics");
		Course course2 = new Course("Biology");
		
		// add courses to instructor
		ins.add(course1);
		ins.add(course2);

		// save the instructor
		System.out.println("Courses saved: " + ins.getCourses());
		app.save(ins);
	}

	/**
	 * Creates and persists a new {@link Student} record in the database.
	 *
	 * @param studentDao the DAO used to execute persistence operations
	 */
	private void createStudent(StudentDao studentDao) {
		// create student object
		System.out.println("Creating student information...");
		Student student = new Student("Mikasa",
				"Ackerman",
				"mikasa.ackerman@example.com");

		// save the student object
		System.out.println("Saving student information...");
		studentDao.save(student);

		// display id of the saved student
		System.out.println("Saved! Generated id: " + student.getId());
	}

	/**
	 * Fetches and displays a {@link Student} entity by its unique ID.
	 *
	 * @param studentDao the DAO used to perform the lookup query
	 */
	private void readStudent(StudentDao studentDao) {
		// retrieve student based on the id
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findById(2);

		// display student
		System.out.println("Found the student: " + student);
	}

	/**
	 * Queries and displays all stored {@link Student} records ordered by last name.
	 *
	 * @param studentDao the DAO used to retrieve all student entities
	 */
	private void queryStudents(StudentDao studentDao) {
		// get list of students
		List<Student> students = studentDao.findAll();

		// display list of students
		for (Student s : students) {
			System.out.println(s);
		}
	}

	/**
	 * Queries and prints all {@link Student} entities matching the specified last
	 * name.
	 *
	 * @param studentDao the DAO used to search students by last name
	 */
	private void queryStudentLastName(StudentDao studentDao) {
		// get list of students
		List<Student> students = studentDao.findByLastName("Yeager");

		// display list of students
		for (Student s : students) {
			System.out.println(s);
		}
	}

	/**
	 * Updates field attributes of an existing {@link Student} entity.
	 *
	 * @param studentDao the DAO used to retrieve and merge updated student details
	 */
	private void updateStudent(StudentDao studentDao) {
		// retrieve student based on id
		int id = 1;
		System.out.println("Student ID: " + id);
		Student student = studentDao.findById(id);

		// update first name and last name
		System.out.println("Updating student name...");
		student.setFirstName("Mikasa");
		student.setLastName("Ackerman");
		student.setEmail("mikasa.ackerman@example.com");

		// update the student
		studentDao.update(student);

		// display the updated student information
		System.out.println("Updated student information: " + student);
	}

	/**
	 * Deletes a specific {@link Student} entity using its ID.
	 *
	 * @param studentDao the DAO used to execute the deletion
	 */
	private void deleteStudent(StudentDao studentDao) {
		int id = 3;
		System.out.println("Delete ID: " + id);

		// delete the student
		studentDao.delete(id);
	}

	/**
	 * Deletes all {@link Student} records from the database table.
	 *
	 * @param studentDao the DAO used to perform the bulk delete operation
	 */
	private void deleteAllStudent(StudentDao studentDao) {
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of rows deleted: " + rowsDeleted);
	}
}