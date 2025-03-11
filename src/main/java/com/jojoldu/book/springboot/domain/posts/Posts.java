package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor// 기본생성자를 만들어 줍니다.
// JPA의 어노테이션
@Entity
public class Posts {
    @Id // PK 나타내는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //==> PK생성규칙인데, auto_increment를 사용한다는 의미.
    private Long id;

    @Column(length = 500, nullable = false)
    // ==> varchar(500) not null
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    // ==> 자료형을 TEXT로 만든다. not null
    private String content;

    // varchar(255) : 기본세팅 , null이 될 수 있습니다.
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
