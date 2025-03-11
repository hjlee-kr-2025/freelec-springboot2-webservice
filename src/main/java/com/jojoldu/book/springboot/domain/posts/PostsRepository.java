package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 데이터베이스로 접근하는 클래스 (인터페이스로 제작합니다)
// DAO 와 같은 역할을 담당하는 클래스 입니다.
// JPA에서는 Repository라는 용어로 사용합니다.
public interface PostsRepository
        extends JpaRepository<Posts, Long> {

}
