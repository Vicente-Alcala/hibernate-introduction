package com.spring.hibernate_demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Student;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	// @Bean
	// CommandLineRunner commandLineRunner(String[] args) {
	// return runner -> {
	// System.out.println("Hello World");
	// };
	// }

	@Bean
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createStudent(studentDao);
			// readStudent(studentDao);
			queryStudents(studentDao);
			// queryStudentLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudent(studentDao);

		};
	}

	private void createStudent(StudentDao studentDao) {
		// create student object
		System.out.println("Creating student information...");
		Student student = new Student("Eren",
				"Yeager",
				"eren.yeager@example.com");

		// save the student object
		System.out.println("Saving student information...");
		studentDao.save(student);

		// display id of the saved student
		System.out.println("Saved! Generated id: " + student.getId());
	}

	private void readStudent(StudentDao studentDao) {
		// retrieve student based on the id
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findById(2);

		// display student
		System.out.println("Found the student: " + student);
	}

	private void queryStudents(StudentDao studentDao) {
		// get list of students
		List<Student> students = studentDao.findAll();

		// display list of students
		for (Student s : students) {
			System.out.println(s);
		}
	}

	private void queryStudentLastName(StudentDao studentDao) {
		// get list of students
		List<Student> students = studentDao.findByLastName("Yeager");

		// display list of students
		for (Student s : students) {
			System.out.println(s);
		}
	}

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

	private void deleteStudent(StudentDao studentDao) {
		int id = 3;
		System.out.println("Delete ID: " + id);

		// delete the student
		studentDao.delete(id);
	}

	private void deleteAllStudent(StudentDao studentDao) {
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of rows deleted: " + rowsDeleted);
	}
}