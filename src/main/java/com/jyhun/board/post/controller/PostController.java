package com.jyhun.board.post.controller;

import com.jyhun.board.board.service.BoardService;
import com.jyhun.board.comment.entity.Comment;
import com.jyhun.board.comment.service.CommentService;
import com.jyhun.board.post.entity.Post;
import com.jyhun.board.post.entity.PostDto;
import com.jyhun.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/{postId}")
    public String getPostDetail(@PathVariable Long postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        model.addAttribute("comments", comments);
        return "post/post";
    }

    @GetMapping("/createPost")
    public String getCreatePost(@RequestParam Long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        return "post/createPost";
    }

    @PostMapping("/createPost")
    public String postCreatePost(@ModelAttribute PostDto postDto, @RequestParam Long boardId) {
        Post post = postDto.toEntity();
        Post createdPost = postService.createPost(boardId, post);
        return "redirect:/boards/" + createdPost.getBoard().getId();
    }

    @GetMapping("/{postId}/editPost")
    public String editPost(@PathVariable Long postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "post/editPost";
    }

    @PostMapping("/{postId}/editPost")
    public String editPost(@PathVariable Long postId, @ModelAttribute PostDto postDto, RedirectAttributes redirectAttributes) {
        Post post = postDto.toEntity();
        postService.updatePost(post, postId);
        redirectAttributes.addAttribute("postId", postId);
        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId, RedirectAttributes redirectAttributes) {
        postService.deletePost(postId);
        redirectAttributes.addFlashAttribute("message", "게시글이 제거되었습니다.");
        return "redirect:/posts";
    }

}
