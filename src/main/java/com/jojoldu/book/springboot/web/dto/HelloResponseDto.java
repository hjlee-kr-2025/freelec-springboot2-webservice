package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//lombok의 @Getter는 모든 멤버변수의 getter만 만들어 줍니다.
@Getter
// 멤버변수중 final로 되어있는 변수를 사용해서 생성자를 만들어줍니다.
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;

    // 변수를 final로 사용하는이유는 값은 변경하지 않고
    // 가져온값은 받기만(사용만) 하려고.
    // 값은 담는 방법은 생성할때 값을 대입합니다.
}
