package com.study.exceptionpractice.web.controller.member;

import com.study.exceptionpractice.web.requestDto.member.MemberRequestDto;
import com.study.exceptionpractice.web.responseDto.member.MemberResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MemberApiController {

    @PostMapping("/api/members/create")
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody @Valid MemberRequestDto memberRequestDto){
        String email = memberRequestDto.getEmail();
        String name = memberRequestDto.getName();
        int age = memberRequestDto.getAge();

        // 실제 회원 등록 로직

        // 회원 등록을 완료하면 완료한 회원의 정보를 MemberResponseDto에 담아 반환한다.
        MemberResponseDto memberResponseDto = new MemberResponseDto(email, name, age);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

}
