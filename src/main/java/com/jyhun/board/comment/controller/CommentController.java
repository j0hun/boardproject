package com.jyhun.board.comment.controller;

import com.jyhun.board.comment.entity.Comment;
import com.jyhun.board.comment.entity.CommentDto;
import com.jyhun.board.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public String createComment(@ModelAttribute CommentDto commentDto, @RequestParam Long postId, RedirectAttributes redirectAttributes) {
        Comment comment = commentDto.toEntity();
        commentService.createComment(postId, comment);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @PostMapping("/{commentId}/editComment")
    public String updateComment(@PathVariable Long commentId, @ModelAttribute CommentDto commentDto, RedirectAttributes redirectAttributes) {
        Comment comment = commentDto.toEntity();
        Comment updatedComment = commentService.updateComment(comment, commentId);
        redirectAttributes.addAttribute("postId", updatedComment.getPost().getId());
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/posts";
    }

}
