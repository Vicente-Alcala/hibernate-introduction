package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * Implementation of the {@link AppDao} interface providing
 * database operations using JPA {@link EntityManager}.
 */
@Repository
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteInstructorIdWithCascade(int theId) {
        // retrieve the instructor
        Instructor in = em.find(Instructor.class, theId);

        // delete the instructor
        em.remove(in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteInstructorDetailWithCascade(int theId) {
        // retrieve the instructor detail
        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        // delete instructor detail
        em.remove(deets);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteInstructorDetailWithoutCascade(int theId) {
        // retrieve instructor detail
        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        // remove the associated object reference
        deets.getInstructor().setInstructorDeets(null);

        // delete instructor detail
        em.remove(deets);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create query
        TypedQuery<Course> query = em.createQuery(
                "FROM Course WHERE instructor.id =:data", Course.class);
        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
        String queryString = "SELECT i FROM Instructor i "
                + "JOIN FETCH i.courses "
                + "WHERE i.id = :data";
        TypedQuery<Instructor> query = em.createQuery(
                queryString, Instructor.class);
        query.setParameter("data", theId);

        Instructor in = query.getSingleResult();
        return in;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(Instructor in) {
        em.merge(in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteInstructorByIdWithoutCascade(int theId) {
        // retrieve instructor
        Instructor in = em.find(Instructor.class, theId);

        // break the associations of courses for instructor
        List<Course> courses = in.getCourses();
        for (Course c : courses) {
            // remove the instructor from the courses
            c.setInstructor(null);
        }

        // delete only the instructor
        // does not include courses associated
        em.remove(in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        // retrieve course
        Course course = em.find(Course.class, theId);

        if (course != null) {
            em.remove(course);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(Course theCourse) {
        em.persist(theCourse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        // create query
        TypedQuery<Course> query = em.createQuery(
                "Select c FROM Course c "
                        + "JOIN FETCH c.review "
                        + "WHERE c.id=:data",
                Course.class);
        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        // create query
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                        + "JOIN FETCH c.students "
                        + "WHERE c.id=:data",
                Course.class);
        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    /**
     * {@inheritDoc}
     */
    public Student findStudentAndCoursesByStudentId(int theId) {
        // create query
        TypedQuery<Student> query = em.createQuery(
                "Select s FROM Student s "
                        + "JOIN FETCH s.courses "
                        + "WHERE s.id=:data",
                Student.class);
        query.setParameter("data", theId);

        // execute query
        Student student = query.getSingleResult();

        return student;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteStudentWithoutCascade(int theId) {
        // retrieve student
        Student student = em.find(Student.class, theId);

        if (student != null) {
            // get the courses
            List<Course> courses = student.getCourses();

            // break the associations of courses for student
            for (Course c : courses) {
                c.getStudents().remove(student);
            }

        }

        // delete only the student
        // does not include the courses associated
        em.remove(student);
    }
}
