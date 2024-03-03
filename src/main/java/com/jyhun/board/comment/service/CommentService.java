package com.jyhun.board.comment.service;

import com.jyhun.board.comment.entity.Comment;
import com.jyhun.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Comment getComment(Long commentId){
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(Comment comment){
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment, Long commentId){
        Comment newComment = commentRepository.findById(commentId).orElse(null);
        newComment.builder()
                .content(comment.getContent())
                .build();
        return commentRepository.save(newComment);
    }

    public void deleteComment(Long commentId){
        Comment newComment = commentRepository.findById(commentId).orElse(null);
        commentRepository.delete(newComment);
    }

}
