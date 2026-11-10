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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a Course entity in the application.
 * Manages relationships with {@link Instructor},
 * {@link Review}, and {@link Student} entities.
 */
@Entity
@Table(name = "course")
public class Course {

    /**
     * Unique identifier for the course generated
     * automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Title or name of the course.
     */
    @Column(name = "title")
    private String title;

    /**
     * The instructor teaching this course (Many-to-One relationship).
     * Excludes remove cascade to prevent deleting the instructor when
     * a course is deleted.
     */
    @ManyToOne(cascade = { CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    /**
     * List of reviews associated with this course (One-to-Many
     * Uni-directional relationship). Cascades all operations so
     * deleting a course removes its reviews.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> review;

    /**
     * List of students enrolled in this course (Many-to-Many relationship).
     * Uses join table {@code course_student} for mapping.
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JoinTable(name = "course_student", joinColumns = @JoinColumn(
        name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Course() {
    }

    /**
     * Constructs a new Course with the specified title.
     *
     * @param title the title of the course
     */
    public Course(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }

    /**
     * Convenience method to add a {@link Review} to this course's review list.
     * Initializes the collection if it has not been created yet.
     *
     * @param rev the review to add to the course
     */
    public void addReview(Review rev) {
        if (review == null) {
            review = new ArrayList<>();
        }
        review.add(rev);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    /**
     * Convenience method to enroll a {@link Student} in this course.
     * Initializes the collection if it has not been created yet.
     *
     * @param stud the student to add to the course
     */
    public void addStudent(Student stud) {
        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(stud);
    }
}
