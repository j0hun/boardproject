package com.jyhun.board.comment.service;

import com.jyhun.board.comment.entity.Comment;
import com.jyhun.board.comment.repository.CommentRepository;
import com.jyhun.board.post.entity.Post;
import com.jyhun.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId).orElse(null);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment, Long commentId) {
        Comment newComment = commentRepository.findById(commentId).orElse(null);
        newComment.update(comment.getContent());
        return commentRepository.save(newComment);
    }

    public void deleteComment(Long commentId) {
        Comment newComment = commentRepository.findById(commentId).orElse(null);
        commentRepository.delete(newComment);
    }

}
