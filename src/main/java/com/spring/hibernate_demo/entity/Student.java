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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Represents a Student entity mapped to the "student" table in the database.
 * Establishes a Many-to-Many bidirectional relationship with {@link Course}.
 */
@Entity
@Table(name = "student")
public class Student {

    /** The unique identifier for the student (Primary Key). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** The first name of the student. */
    @Column(name = "first_name")
    private String firstName;

    /** The last name of the student. */
    @Column(name = "last_name")
    private String lastName;

    /** The email address of the student. */
    @Column(name = "email")
    private String email;

    /**
     * List of courses enrolled by this student (Many-to-Many
     * relationship). Mapped by the {@code students} field in
     * the Course entity (Inverse side). Configured with Lazy
     * loading and excludes remove cascade to protect course
     * records when deleting students.
     */
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY,
                cascade = { CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.PERSIST,
                            CascadeType.REFRESH })
    private List<Course> courses;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Student() {
    }

    /**
     * Constructs a new Student instance with specified personal details.
     *
     * @param firstName the student's first name
     * @param lastName  the student's last name
     * @param email     the student's email address
     */
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    @Override
    public String toString() {
        return "Student [id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email + "]";
    }

    /**
     * Convenience method to enroll this student in a {@link Course}.
     * Ensures bidirectional harmony by adding this student to the
     * course's student list as well.
     *
     * @param course the course to add
     */
    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.addStudent(this);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
