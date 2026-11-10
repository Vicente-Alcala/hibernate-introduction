package com.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents the detailed profile associated
 * with an {@link Instructor}. Establishes a
 * bidirectional One-to-One relationship back
 * to the Instructor entity.
 */
@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    /**
     * Unique identifier for the instructor detail
     * record generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * The primary subject taught by the instructor.
     */
    @Column(name = "subject")
    private String subject;

    /**
     * The hobby or personal interest of the instructor.
     */
    @Column(name = "hobby")
    private String hobby;

    /**
     * The associated {@link Instructor} entity (Inverse side
     * of the bidirectional One-to-One relationship). Mapped by
     * the {@code instructorDeets} field in the Instructor entity.
     * Excludes remove cascade so deleting detail records does not
     * delete the instructor.
     */
    @OneToOne(mappedBy = "instructorDeets", cascade = { CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH })
    private Instructor instructor;

    /**
     * Default no-argument constructor required by JPA.
     */
    public InstructorDetail() {
    }

    /**
     * Constructs a new InstructorDetail with the specified subject and hobby.
     *
     * @param subject the subject area taught
     * @param hobby   the instructor's personal hobby
     */
    public InstructorDetail(String subject, String hobby) {
        this.subject = subject;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "InstructorDetail [id=" + id +
                ", subject=" + subject +
                ", hobby=" + hobby + "]";
    }

}
