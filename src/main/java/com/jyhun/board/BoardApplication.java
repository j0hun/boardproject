package com.jyhun.board;

import com.jyhun.board.board.repository.BoardRepository;
import com.jyhun.board.comment.repository.CommentRepository;
import com.jyhun.board.post.repository.PostRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

    @Bean
    @Profile("local")
    public DataInit boardDataInit(BoardRepository boardRepository, PostRepository postRepository, CommentRepository commentRepository) {
        return new DataInit(boardRepository, postRepository, commentRepository);
    }

}
