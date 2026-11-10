package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a Review entity for a course.
 * Used in a One-to-Many Uni-directional
 * relationship managed by {@link Course}.
 */
@Entity
@Table(name = "review")
public class Review {

    /**
     * Unique identifier for the review generated
     * automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * The comment or feedback text for the course review.
     */
    @Column(name = "comments")
    private String comments;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Review() {
    }

    /**
     * Constructs a new Review with the specified comment text.
     *
     * @param comments the feedback or review comments
     */
    public Review(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + ", comments=" + comments + "]";
    }

}
