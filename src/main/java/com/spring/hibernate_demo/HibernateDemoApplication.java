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
import com.spring.hibernate_demo.entity.Review;
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
			// deleteInstructorDetailWithCascade(app);
			// deleteInstructorDetailWithoutCascade(app);
			// createInstructorWithCourses(app);
			// findInstructorWithCourses(app);
			// findCoursesForInstructor(app);
			// findInstructorWithCoursesJoinFetch(app);
			// updateInstructor(app);
			// deleteInstructorOnly(app);
			// deleteCourseOnly(app);
			// createCourseAndReviews(app);
			retrieveCourseAndReviews(app);
			// deleteCourseAndReviews(app);
			// createCourseAndStudents(app);
			// findCourseAndStudents(app);
			// findStudentAndCourses(app);
			// addMoreCoursesForStudent(app);
			// deleteCourseById(app);
			// deleteStudent(app);
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
		int id = 6;
		System.out.println("Delete id: " + id);

		app.deleteInstructorIdWithCascade(id);
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

	private void deleteInstructorDetailWithCascade(AppDao app) {
		int id = 6;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetailWithCascade(id);
		System.out.println("Deleted successfully!");
	}

	private void deleteInstructorDetailWithoutCascade(AppDao app) {
		int id = 6;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetailWithoutCascade(id);
		System.out.println("Deleted successfully!");
	}

	private void createInstructorWithCourses(AppDao app) {
		// create the instructor and details
		Instructor ins = new Instructor("Annie",
				"Leonhart",
				"annie.leonhart@example.com");

		InstructorDetail deets = new InstructorDetail("Art of War", "Fulfilling a warrior's duty");

		// associate the objects
		ins.setInstructorDeets(deets);

		// create courses
		Course course1 = new Course("English");
		Course course2 = new Course("Japanese 101");

		// add courses to instructor
		ins.add(course1);
		ins.add(course2);

		// save the instructor
		System.out.println("Courses saved: " + ins.getCourses());
		app.save(ins);
	}

	private void findInstructorWithCourses(AppDao app) {
		int id = 4;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	private void findCoursesForInstructor(AppDao app) {
		int id = 4;

		// find the instructor
		System.out.println("Find instructor id: " + id);
		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);

		// find courses for instructor
		System.out.println("Courses from id: " + id);
		List<Course> courses = app.findCoursesByInstructorId(id);

		// associate the objects
		ins.setCourses(courses);

		// get the courses
		System.out.println(ins.getCourses());
	}

	private void findInstructorWithCoursesJoinFetch(AppDao app) {
		int id = 4;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());
	}

	private void updateInstructor(AppDao app) {
		int id = 4;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		// change instructor's data
		ins.setLastName("Kirstein");

		// update the data
		System.out.println("Updated!");
		app.update(ins);
	}

	private void deleteInstructorOnly(AppDao app) {
		int id = 3;
		System.out.println("Delete instructor id: " + id);

		app.deleteInstructorByIdWithoutCascade(id);
		System.out.println("Deleted successfully!");
	}

	private void deleteCourseOnly(AppDao app) {
		int id = 7;
		System.out.println("Delete course id: " + id);

		app.deleteCourseById(id);
		System.out.println("Course deleted successfully");
	}

	private void createCourseAndReviews(AppDao app) {
		// create a course
		Course course = new Course("How to Speed Run Spring in 1 Day");
		// add some reviews
		course.addReview(new Review("Great speed 2x...loved it!!!"));
		course.addReview(new Review("I wanted the speed 0.75x :(("));
		course.addReview(new Review("Hoping to do a speed of 3x :D. Anyways, nice!"));

		// save the course
		System.out.println("Course saved!");
		app.save(course);
	}

	private void retrieveCourseAndReviews(AppDao app) {
		int id = 13;
		System.out.println("Get course and reviews from id: " + id);
		// get the course and reviews
		Course course = app.findCourseAndReviewsByCourseId(id);

		// print the course
		System.out.println(course);

		// print the reviews
		System.out.println(course.getReview());
	}

	private void deleteCourseAndReviews(AppDao app) {
		int id = 14;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted successfully!");
	}

	private void createCourseAndStudents(AppDao app) {
		// create a course
		Course course = new Course("How To Be a FullStack Dev in Half A Day");

		// create the students
		Student student1 = new Student("Erwin", "Smith", "erwin.smith@example.com");
		Student student2 = new Student("Levi", "Ackerman", "levi.ackerman@example.com");

		// add student to the course
		course.addStudent(student1);
		course.addStudent(student2);

		// save the course and associated students
		app.save(course);
		System.out.println("Successfully enrolled!");
	}

	private void findCourseAndStudents(AppDao app) {
		int id = 9;
		Course course = app.findCourseAndStudentByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students " + course.getStudents());
	}

	private void addMoreCoursesForStudent(AppDao app) {
		int id = 8;
		Student student = app.findStudentAndCoursesByStudentId(id);

		Course course1 = new Course("How to Test without Running Application");
		Course course2 = new Course("How to Develop without Computer");
		Course course3 = new Course("How to Be a Legit Developer");

		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);

		System.out.println("Successfully enrolled to new courses!");

		app.update(student);
	}

	private void deleteCourseById(AppDao app) {
		int id = 12;
		System.out.println("Delete course id: " + id);

		app.deleteCourseById(id);
		System.out.println("Course deleted successfully");
	}

	private void deleteStudent(AppDao app) {
		int id = 1;
		System.out.println("Delete student: " + id);

		app.deleteStudentWithoutCascade(id);
		System.out.println("Deleted successfully!");
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

	private void findStudentAndCourses(AppDao app) {
		int id = 7;
		Student student = app.findStudentAndCoursesByStudentId(id);

		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
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