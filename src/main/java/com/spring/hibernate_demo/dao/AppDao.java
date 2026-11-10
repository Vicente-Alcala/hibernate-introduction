package com.spring.hibernate_demo.dao;

import java.util.List;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Review;
import com.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object (DAO) interface for managing persistence operations
 * across Instructor, InstructorDetail, Course, Review, and Student entities.
 */
public interface AppDao {

    /**
     * Persists a new {@link Instructor} along with any
     * cascade-configured associated entities.
     *
     * @param theInstructor the instructor entity to save
     */
    void save(Instructor theInstructor);

    /**
     * Retrieves an {@link Instructor} by its primary key ID.
     *
     * @param theId the unique identifier of the instructor
     * @return the matching instructor, or {@code null} if not found
     */
    Instructor findInstructorById(int theId);

    /**
     * Deletes an {@link InstructorDetail} record and cascades the deletion
     * to the associated {@link Instructor}.
     *
     * @param theId the unique identifier of the instructor detail to delete
     */
    void deleteInstructorIdWithCascade(int theId);

    /**
     * Retrieves an {@link InstructorDetail} entity by its primary key ID.
     *
     * @param theId the unique identifier of the instructor detail record
     * @return the matching instructor detail, or {@code null} if not found
     */
    InstructorDetail findInstructorDetailById(int theId);

    /**
     * Deletes an {@link InstructorDetail} record and cascades the deletion
     * to the associated {@link Instructor}.
     *
     * @param theId the unique identifier of the instructor detail to delete
     */
    void deleteInstructorDetailWithCascade(int theId);

    /**
     * Deletes an {@link InstructorDetail} record without deleting
     * the associated {@link Instructor}.
     *
     * @param theId the unique identifier of the instructor detail to delete
     */
    void deleteInstructorDetailWithoutCascade(int theId);

    /**
     * Finds all {@link Course} entities associated with a specific instructor
     * ID via lazy loading query.
     *
     * @param theId the unique identifier of the instructor
     * @return a list of courses belonging to the instructor
     */
    List<Course> findCoursesByInstructorId(int theId);

    /**
     * Retrieves an {@link Instructor} and eagerly fetches associated courses
     * in a single query using JOIN FETCH.
     *
     * @param theId the unique identifier of the instructor
     * @return the instructor populated with courses
     */
    Instructor findInstructorByIdJoinFetch(int theId);

    /**
     * Updates an existing {@link Instructor} record in the database.
     *
     * @param in the instructor entity containing updated values
     */
    void update(Instructor in);

    /**
     * Deletes an {@link InstructorDetail} record without deleting
     * the associated {@link Instructor}.
     *
     * @param theId the unique identifier of the instructor detail to delete
     */
    void deleteInstructorByIdWithoutCascade(int theId);

    /**
     * Deletes a single {@link Course} entity by its primary key ID.
     *
     * @param theId the unique identifier of the course to delete
     */
    void deleteCourseById(int theId);

    /**
     * Persists a new {@link Course} entity along with its associated records
     * (e.g., reviews or student links).
     *
     * @param theCourse the course entity to save
     */
    void save(Course theCourse);

    /**
     * Fetches a {@link Course} and eagerly joins its associated {@link Review}
     * entities.
     *
     * @param theId the unique identifier of the course
     * @return the course entity populated with its reviews
     */
    Course findCourseAndReviewsByCourseId(int theId);

    /**
     * Fetches a {@link Course} and eagerly joins its enrolled {@link Student}
     * entities.
     *
     * @param theId the unique identifier of the course
     * @return the course entity populated with its enrolled students
     */
    Course findCourseAndStudentByCourseId(int theId);

    /**
     * Fetches a {@link Student} and eagerly joins all {@link Course} entities
     * they are enrolled in.
     *
     * @param theId the unique identifier of the student
     * @return the student entity populated with their courses
     */
    Student findStudentAndCoursesByStudentId(int theId);

    /**
     * Updates an existing {@link Student} record in the database.
     *
     * @param student the student entity containing updated values
     */
    void update(Student student);

    /**
     * Deletes a {@link Student} by ID without deleting associated
     * {@link Course} records, unlinking student references from join
     * tables first.
     * <p>
     * Note: Renamed from {@code deleteStudentById(int)} to clarify
     * non-cascading behavior.
     * </p>
     *
     * @param theId the unique identifier of the student to delete
     */
    void deleteStudentWithoutCascade(int theId);
}
