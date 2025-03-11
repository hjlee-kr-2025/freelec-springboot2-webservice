package com.jojoldu.book.springboot.web;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired// 자동생성진행
    private TestRestTemplate restTemplate;

    @Test
    public void mainPageLoading() {
        // "/"페이지를 호출한후 결과값을 body에 담는다.
        String body = this.restTemplate
                .getForObject("/", String.class);

        Assertions.assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
        // contains는 body안에 ""에 적힌 문자열이 있으면 성공을 의미합니다.

    }
}
