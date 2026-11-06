package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * JPA implementation of the {@link StudentDao} interface.
 * Handles database operations for {@link Student} entities using {@link EntityManager}.
 */
@Repository
public class StudentDaoImplement implements StudentDao {

    /** The JPA entity manager used to interact with the persistence context. */
    private EntityManager em;

    /**
     * Constructs the DAO implementation with an injected {@link EntityManager}.
     *
     * @param em the JPA entity manager instance provided by Spring
     */
    public StudentDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> query = em.createQuery(
                "FROM Student order by lastName", Student.class);

        // return query results
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> findByLastName(String lastName) {
        // create query
        TypedQuery<Student> query = em.createQuery(
                "FROM Student WHERE lastName=:data", Student.class);

        // set query parameter
        query.setParameter("data", lastName);
        // return query results
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        // retrieve the student
        Student student = em.find(Student.class, id);

        // delete the student
        em.remove(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public int deleteAll() {
        int numRows = em.createQuery("DELETE FROM Student").executeUpdate();
        return numRows;
    }
}
