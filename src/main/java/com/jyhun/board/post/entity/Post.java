package com.jyhun.board.post.entity;

import com.jyhun.board.audit.BaseEntity;
import com.jyhun.board.board.entity.Board;
import com.jyhun.board.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "post")
    private final List<Comment> commentList = new ArrayList();

    public void setBoard(Board board) {
        this.board = board;
        this.board.getPostList().add(this);
    }

    @Builder(toBuilder = true)
    public Post(Board board, String title, String content) {
        this.board = board;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
