package com.jyhun.board.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {

    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(this.content)
                .build();
    }

}
