package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // 상속받는 클래스(JPA Entity) BaseTimeEntity클래스내의 변수들을
// 칼럼으로 인식합니다.
@EntityListeners(AuditingEntityListener.class)
// 생성,수정시간을 자동으로 기록할때 사용하는 어노테이션
public abstract class BaseTimeEntity {

    @CreatedDate//생성시간을 자동할당
    private LocalDateTime createdDate;//생성날짜

    @LastModifiedDate//수정이 끝난 시간으로 update
    private LocalDateTime modifiedDate;//수정날짜
}
