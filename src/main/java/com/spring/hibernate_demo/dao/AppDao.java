package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;

public interface AppDao {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorId(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetail(int theId);
}
