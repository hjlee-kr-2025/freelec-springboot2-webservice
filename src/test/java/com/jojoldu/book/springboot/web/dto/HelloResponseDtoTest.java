package com.jojoldu.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    // Test를 실행할 메서드 위에 @Test(junit)을 붙입니다.
    @Test
    public void lombok_Test() {
        // 생성할때 값을 정하고
        // 생성을 진행합니다.
        // 생성된 값과 정한값을 비교해서 같은지 확인
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        // aseertThat(검증하고싶은것).isEqualTo(비교값);
        // isEqualTo()는 값(데이터)가 같은지를 비교하는 메서드
    }
}
