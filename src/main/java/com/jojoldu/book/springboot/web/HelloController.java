package com.jojoldu.book.springboot.web;

/* 자동생성 어노테이션
- @Controller, @RestController - url 메뉴 선택
- @Service - Service 클래스
- @Ropository - 저장소
- @~~~Advice - 예외처리를 위한 클래스
* */

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // @GetMapping 주소창에 url을 치면 들어오는 곳: get방식
    // 메인주소/hello
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    // @GetMapping

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            @RequestParam("name") String name,
            @RequestParam("amount") int amount
    ) {
        // @RequestParam 에 적힌 값은 get방식에서는
        // 웹브라우저 주소창에 적힌 변수이름과 매칭됩니다.
        // localhost:8080/hello/dto?name=a&amount=1
        return new HelloResponseDto(name, amount);
    }
}
