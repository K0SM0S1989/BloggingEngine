package org.example.BloggingProject.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Tags2Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post postId;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tagId;
}
