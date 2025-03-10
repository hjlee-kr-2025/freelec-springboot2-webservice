package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//=> import static 을 사용하는 이유는 static 메서드를 사용할때 클래스이름.메서드()로
// 사용하지 않고 메서드 이름으로만 사용할 수 있기 때문입니다.
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
//===> @Controller, @RestController 를 테스트 할 때 사용합니다.
public class HelloControllerTest {

    @Autowired
    // 자동생성해서 사용할 수 있도록 합니다.
    private MockMvc mvc;
    //==> WEB 테스트를 진행시 사용하는 클래스

    @Test
    public void hello_return() throws Exception {
        String hello = "hello";//테스트 결과 확인용 변수

        mvc.perform(get("/hello"))
                //==> URL에서 주소입력하는 것과 동일한 일을 진행
                .andExpect(status().isOk())
                //==> 200 -> 정상적인 페이지임을 알려줍니다.
                .andExpect(content().string(hello));
                //==> 결과값이 "hello" 문자열이 넘어오는지 확인합니다.

        // 위의 요소를 모두 통과했을때 테스트 결과가 ok가 됩니다.
    }
}
