package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor    // final 변수사용해서 생성자구성
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // 데이터베이스와 연결되어 실행되는 메서드
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
        // 데이터를 DB에 저장하고, 저장할때 만들어진 id값을 리턴합니다.
    }
}
