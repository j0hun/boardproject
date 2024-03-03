package com.jyhun.board.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Builder
    public Board(String name, String description, LocalDateTime createAt, LocalDateTime updateAt) {
        this.name = name;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
