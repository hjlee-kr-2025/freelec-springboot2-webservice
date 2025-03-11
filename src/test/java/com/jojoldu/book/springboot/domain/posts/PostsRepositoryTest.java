package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

// 데이터베이스가 잘 동작하는지 테스트 하는 클래스
@RunWith(SpringRunner.class)
@SpringBootTest// h2데이터베이스를 자동으로 실행해줍니다.
public class PostsRepositoryTest {
    
    @Autowired//자동생성
    PostsRepository postsRepository;
    
    @After// 테스트가 끝난후 실행되는 메서드입니다.
    public void cleanup() { 
        postsRepository.deleteAll();
        //=> 자료를 모두 지운다.
    }
    
    @Test
    public void contents_save_load() {
        // 데이터 베이스에 넣은 데이터
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "tester";
        
        // 데이터베이스에 데이터 추가
        // sql insert 명령실행
        postsRepository.save(
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author(author)
                        .build()
        );

        // 리스트 담기 : select
        List<Posts> postsList = postsRepository.findAll();

        // 데이터 확인
        Posts posts = postsList.get(0);// 첫번째 데이터를 가져옵니다.
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);
    } // end of contents_save_load()

    @Test
    public void baseTimeEntity_save() {
        LocalDateTime now = LocalDateTime.of(2025,3,11,15,23,0);
        postsRepository.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                .build());
        // 데이터 가져오기
        List<Posts> postsList = postsRepository.findAll();

        // 첫번째 글을 세팅
        Posts posts = postsList.get(0);

        System.out.println(">>>>> createdDate="+
                posts.getCreatedDate()+", modifiedDate="+
                posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isEqualTo(now);
        assertThat(posts.getModifiedDate()).isEqualTo(now);
    } // end of baseTimeEntity_save()
}
