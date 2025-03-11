package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//==> 웹에서 넘어오는 데이터 확인용 메서드

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    
    @LocalServerPort
    // RANDOM_PORT 로 사용할때 쓰는 어노테이션
    private int port;
    
    @Autowired// 자동생성해서 사용
    // controller를 통해 JPA를 테스트할때 사용하는 템플릿
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After // 테스트가 끝나고 실행 - 테이블자료삭제
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_save() throws Exception {
        // 데이터 세팅 : 3가지
        String title = "title";
        String content = "content";
        String author = "author";

        // 데이터베이스에 등록을 위한 데이터 준비
        PostsSaveRequestDto requestDto
                = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        
        // Post방식으로 페이지 호출되는 url 생성
        String url = "http://localhost:"+port+"/api/v1/posts";
        
        // 페이지 호출(데이터와 함께)
        ResponseEntity<Long> responseEntity
                = restTemplate.postForEntity(url, requestDto, Long.class);
        // postForEntity(주소, post로 넘어갈 데이터, pk자료형클래스)
        
        // 받은 데이터 확인 
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //==> status가 200번인지 확인합니다.(정상이면 200)
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        //==> 결과값이 0L 보다 큰값이면 등록이 잘 되었다는 의미입니다.
        // 데이터베이스에서 insert시 등록된 열개수가 리턴값으로 넘어옵니다.

        // 데이터베이스에 값을 불러와서 check
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);
    }   // end of posts_save()

    @Test
    public void posts_update() throws Exception {
        // 데이터베이스에 수정전 값 세팅
        Posts savePosts = postsRepository.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                .build());

        Long updateId = savePosts.getId();
        // 수정할 값 세팅
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto =
                PostsUpdateRequestDto.builder()
                        .title(expectedTitle)
                        .content(expectedContent)
                        .build();

        // url세팅
        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity
                = new HttpEntity<>(requestDto);
        
        // url 접근 -> 컨트롤러를 통해 데이터베이스 접근 -> 수정작업
        ResponseEntity<Long> responseEntity
                = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class );
        
        // 결과 확인
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        
        // 수정이 되었는지 데이터베이스에서 값을 꺼낸후 체크
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    } // end of posts_update()
}
