package com.spring.hibernate_demo.dao;

import java.util.List;
import com.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object (DAO) interface for performing CRUD operations 
 * on {@link Student} entities in the database.
 */
public interface StudentDao {

    /**
     * Persists a new student record into the database.
     *
     * @param theStudent the {@link Student} object to be saved
     */
    void save(Student theStudent);

    /**
     * Retrieves a student by their unique primary key ID.
     *
     * @param id the unique identifier of the student
     * @return the {@link Student} entity if found; {@code null} otherwise
     */
    Student findById(Integer id);

    /**
     * Retrieves all student records stored in the database.
     *
     * @return a {@link List} of all {@link Student} entities
     */
    List<Student> findAll();

    /**
     * Finds and retrieves students whose last name matches the specified search term.
     *
     * @param lastName the last name to search for
     * @return a {@link List} of matching {@link Student} entities
     */
    List<Student> findByLastName(String lastName);

    /**
     * Updates an existing student record in the database.
     *
     * @param theStudent the {@link Student} object containing updated details
     */
    void update(Student theStudent);

    /**
     * Deletes a student record from the database using their unique ID.
     *
     * @param id the unique identifier of the student to be removed
     */
    void delete(Integer id);

    /**
     * Deletes all student records from the database.
     *
     * @return the total number of student records deleted
     */
    int deleteAll();
}
