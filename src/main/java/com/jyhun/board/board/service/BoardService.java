package com.jyhun.board.board.service;

import com.jyhun.board.board.entity.Board;
import com.jyhun.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Board board, Long boardId) {
        Board newBoard = boardRepository.findById(boardId).orElse(null);
        newBoard.update(board.getName(), board.getDescription());
        return boardRepository.save(newBoard);
    }

    public void deleteBoard(Long boardId) {
        Board newBoard = boardRepository.findById(boardId).orElse(null);
        boardRepository.delete(newBoard);
    }

}
