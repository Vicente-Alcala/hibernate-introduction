package com.spring.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents an Instructor entity in the application.
 * Manages relationships with {@link InstructorDetail}
 * and {@link Course} entities.
 */
@Entity
@Table(name = "instructor")
public class Instructor {

    /**
     * Unique identifier for the instructor generated
     * automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * First name of the instructor.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Last name of the instructor.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Email address of the instructor.
     */
    @Column(name = "email")
    private String email;

    /**
     * Associated instructor detail profile (One-to-One Uni
     * /Bi-directional mapping). Cascades all persistence
     * operations so actions on instructor cascade to details.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDeets;

    /**
     * List of courses taught by this instructor (One-to-Many
     * Bi-directional mapping). Configured for Lazy loading and
     * excludes remove cascade to prevent deleting courses when
     * an instructor is removed.
     */
    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH })
    private List<Course> courses;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Instructor() {
    }

    /**
     * Constructs a new Instructor with the specified basic details.
     *
     * @param firstName the first name of the instructor
     * @param lastName  the last name of the instructor
     * @param email     the email address of the instructor
     */
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Convenience method to link a {@link Course} object
     * with this instructor. Maintains bidirectional harmony
     * by updating both sides of the relationship.
     *
     * @param tcourse the course to be added to this instructor
     */
    public void add(Course tcourse) {
        // initializes list if it's null
        if (this.courses == null) {
            this.courses = new ArrayList<>();
        }
        // add the course to instructor's list
        this.courses.add(tcourse);
        // tell course who instructor is
        // 'this' refers to the current Instructor object
        tcourse.setInstructor(this);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDeets() {
        return instructorDeets;
    }

    public void setInstructorDeets(InstructorDetail instructorDeets) {
        this.instructorDeets = instructorDeets;
    }

    @Override
    public String toString() {
        return "Instructor [id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email + "]";
    }
}
