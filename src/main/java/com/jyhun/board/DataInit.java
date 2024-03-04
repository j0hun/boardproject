package com.jyhun.board;

import com.jyhun.board.board.entity.Board;
import com.jyhun.board.board.repository.BoardRepository;
import com.jyhun.board.comment.entity.Comment;
import com.jyhun.board.comment.repository.CommentRepository;
import com.jyhun.board.post.entity.Post;
import com.jyhun.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class DataInit {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        boardRepository.save(new Board("IT 게시판", "IT 분야에 관한 책에 대해서 리뷰하고 추천하는 게시판입니다."));
        boardRepository.save(new Board("소설 게시판", "소설 분야에 관한 책에 대해서 리뷰하고 추천하는 게시판입니다."));
        boardRepository.save(new Board("자기계발 게시판", "자기계발 분야에 관한 책에 대해서 리뷰하고 추천하는 게시판입니다."));
        Board board = boardRepository.findById(1L).orElseThrow(() -> new RuntimeException());

        postRepository.save(new Post(board, "자바의 정석", "자바에 대해서 많은것을 배울수 있어 이 책을 추천합니다."));
        postRepository.save(new Post(board, "스프링부트3 백엔드 개발자되기", "스프링부트3에 대해서 많은것을 배울수 있어서 이 책을 추천합니다."));
        postRepository.save(new Post(board, "데이터베이스 개론", "데이터베이스에 대해서 자세하게 배울수 있어서 이 책을 추천합니다."));
        Post post = postRepository.findById(1L).orElseThrow(() -> new RuntimeException());

        commentRepository.save(new Comment(post, "자바에 대해서 자세하게 배울 수 있는 책이였습니다. 저도 추천합니다."));
    }
}
