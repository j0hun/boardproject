package com.jyhun.board.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDto {

    private String name;
    private String description;

    public Board toEntity() {
        return Board.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }

}
