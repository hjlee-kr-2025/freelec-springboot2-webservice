package com.jojoldu.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //getter
@NoArgsConstructor  //기본생성자
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    
    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
