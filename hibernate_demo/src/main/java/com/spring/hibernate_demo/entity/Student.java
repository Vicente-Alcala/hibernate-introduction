package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a Student entity mapped to the "student" table in the database.
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

    /**
     * Gets the unique student ID.
     *
     * @return the student ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique student ID.
     *
     * @param id the student ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the student's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the student's first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the student's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the student's last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the student's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the Student object.
     *
     * @return a formatted string containing student fields
     */
    @Override
    public String toString() {
        return "Student [id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email + "]";
    }

}
