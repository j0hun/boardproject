package com.jyhun.board.post.repository;

import com.jyhun.board.board.entity.Board;
import com.jyhun.board.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByBoardOrderByCreatedAtDesc(Board board, Pageable pageable);

    Page<Post> findAllByBoardAndTitleContaining(Board board, String keyword, Pageable pageable);

}
