package com.welab.backend_post.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Table(name = "post", indexes = {
        @Index(columnList = "user_id"),
        @Index(columnList = "created_datetime"),
        @Index(columnList = "updated_datetime")
})
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "title", nullable = false)
    @Getter
    private String title;

    @Column(name = "content", nullable = false)
    @Getter
    private String content;

    public void setPost(String title, String contents) {
        this.title = title;
        this.content = contents;
        this.updatedDatetime = LocalDateTime.now();
    }

    @Column(name = "user_id", nullable = false)
    @Getter
    @Setter
    private String userId;

    @Column(name = "created_datetime", nullable = false)
    @Getter
    private LocalDateTime createdDatetime = LocalDateTime.now();

    @Column(name = "updated_datetime")
    @Getter
    private LocalDateTime updatedDatetime;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostComment> comments = new ArrayList<>();

    public List<PostComment> getComments() {
        return this.comments;
    }

    public void addComment(PostComment comment) {
        comment.setPost(this);
        this.comments.add(comment);
    }
}