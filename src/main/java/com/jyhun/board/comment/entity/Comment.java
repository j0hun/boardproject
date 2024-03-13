package com.jyhun.board.comment.entity;

import com.jyhun.board.audit.BaseEntity;
import com.jyhun.board.post.entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
        this.post.getCommentList().add(this);
    }

    @Builder(toBuilder = true)
    public Comment(Post post, String content) {
        this.post = post;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}
