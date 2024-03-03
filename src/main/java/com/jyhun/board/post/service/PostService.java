package com.jyhun.board.post.service;

import com.jyhun.board.post.entity.Post;
import com.jyhun.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post getPost(Long postId){
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public Post updatePost(Post post, Long postId){
        Post newPost = postRepository.findById(postId).orElse(null);
        newPost.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
        return postRepository.save(newPost);
    }

    public void deletePost(Long postId){
        Post newPost = postRepository.findById(postId).orElse(null);
        postRepository.delete(newPost);
    }

}
