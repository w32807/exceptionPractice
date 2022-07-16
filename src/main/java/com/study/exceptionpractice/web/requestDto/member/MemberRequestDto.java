package com.study.exceptionpractice.web.requestDto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

    @Email(message = "이메일 형식을 확인 해 주세요.")
    @NotBlank(message = "이메일을 입력 해 주세요.")
    private String email;

    @NotBlank(message = "이름을 입력 해 주세요.")
    private String name;

    @Range(min =0, max = 200, message = "나이는 0 ~ 200사이로 입력 가능합니다.")
    private int age;

}
