package com.jojoldu.book.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // JPA Auditing활성화 (생성시간,수정시간 자동기록)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
//=> SpringBoot가 시작되는 프로그램(Class)
//=> @SpringBootApplication
//=> Java servlet 프로젝트에서 DispatherServlet 클래스가 하는 역할을
//담당하게 됩니다.
