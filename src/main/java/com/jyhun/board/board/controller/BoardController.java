package com.jyhun.board.board.controller;

import com.jyhun.board.board.entity.Board;
import com.jyhun.board.board.entity.BoardDto;
import com.jyhun.board.board.service.BoardService;
import com.jyhun.board.post.entity.Post;
import com.jyhun.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;

    @GetMapping
    public String getBoards(Model model) {
        List<Board> boards = boardService.getBoards();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    @GetMapping("/{boardId}")
    public String getBoard(@PathVariable Long boardId,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(required = false) String keyword,
                           Model model) {
        Board board = boardService.getBoard(boardId);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage = postService.getPostsByBoardAndKeyword(board, keyword, pageRequest);

        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);
        return "board/board";
    }

    @GetMapping("/createBoard")
    public String getCreateBoard() {
        return "board/createBoard";
    }

    @PostMapping("/createBoard")
    public String postCreateBoard(@ModelAttribute BoardDto boardDto) {
        Board board = boardDto.toEntity();
        boardService.createBoard(board);

        return "redirect:/boards";
    }

    @GetMapping("/{boardId}/editBoard")
    public String editBoard(@PathVariable Long boardId, Model model) {
        Board board = boardService.getBoard(boardId);
        model.addAttribute("board", board);
        return "board/editBoard";
    }

    @PostMapping("/{boardId}/editBoard")
    public String editBoardPost(@PathVariable Long boardId, @ModelAttribute BoardDto boardDto) {
        Board board = boardDto.toEntity();
        boardService.updateBoard(board, boardId);
        return "redirect:/boards";
    }

    @DeleteMapping("/{boardId}/deleteBoard")
    public String deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);

        return "redirect:/boards";
    }

}
