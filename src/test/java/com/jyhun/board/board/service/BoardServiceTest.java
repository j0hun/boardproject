package com.jyhun.board.board.service;

import com.jyhun.board.board.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    void Test(){
        Board board = new Board("테스트1","테스트2");
        Board createBoard = boardService.createBoard(board);
        assertEquals(createBoard.getName(),board.getName());
    }

}