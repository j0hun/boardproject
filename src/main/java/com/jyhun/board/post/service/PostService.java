package com.jyhun.board.post.service;

import com.jyhun.board.board.entity.Board;
import com.jyhun.board.board.repository.BoardRepository;
import com.jyhun.board.post.entity.Post;
import com.jyhun.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Transactional(readOnly = true)
    public Page<Post> getPostsByBoardAndKeyword(Board board, String keyword, PageRequest pageRequest) {
        if (keyword != null && !keyword.isEmpty()) {
            return postRepository.findAllByBoardAndTitleContaining(board, keyword, pageRequest);
        }
        return postRepository.findAllByBoardOrderByCreatedAtDesc(board, pageRequest);
    }

    public Post createPost(Long boardId, Post post) {
        Board board = boardRepository.findById(boardId).orElse(null);
        post.setBoard(board);
        return postRepository.save(post);
    }

    public Post updatePost(Post post, Long postId) {
        Post newPost = postRepository.findById(postId).orElse(null);
        newPost.update(post.getTitle(), post.getContent());
        return postRepository.save(newPost);
    }

    public void deletePost(Long postId) {
        Post newPost = postRepository.findById(postId).orElse(null);
        postRepository.delete(newPost);
    }

}
