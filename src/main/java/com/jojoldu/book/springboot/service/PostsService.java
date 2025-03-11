package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor    // final 변수사용해서 생성자구성
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // 데이터베이스와 연결되어 실행되는 메서드
    @Transactional
    // 서비스를 실행시 여러개의 쿼리가 실행될 수도 있습니다. 이때 한개라도 sql쿼리명령이 실패가 발생하면
    // 이 서비스에서 실행되는 쿼리가 취소됩니다.(실행전상태로 데이터베이스가 유지)
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
        // 데이터를 DB에 저장하고, 저장할때 만들어진 id값을 리턴합니다.
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }


    // id로 해당글을 가져오는 부분
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );

        return new PostsResponseDto(entity);
    }
}
