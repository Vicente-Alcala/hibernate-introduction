package com.spring.hibernate_demo.dao;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorId(int theId) {
        // retrieve the instructor
        Instructor in = em.find(Instructor.class, theId);

        // delete the instructor
        em.remove(in);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    // delete with cascade
    // @Override
    // @Transactional
    // public void deleteInstructorDetail(int theId) {
    //     // retrieve the instructor detail
    //     InstructorDetail deets = em.find(InstructorDetail.class, theId);

    //     // delete instructor detail
    //     em.remove(deets);
    // }

    // delete without cascade
    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        // retrieve instructor detail
        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        // remove the associated object reference
        deets.getInstructor().setInstructorDeets(null);

        // delete instructor detail
        em.remove(deets);
    }
}
