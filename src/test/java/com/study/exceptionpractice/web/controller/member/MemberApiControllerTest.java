package com.study.exceptionpractice.web.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.exceptionpractice.web.requestDto.member.MemberRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class MemberApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/members/create 요청 시 회원을 등록한다.")
    public void createMemberTest1() throws Exception{
        MemberRequestDto member = new MemberRequestDto("admin@google.com", "admin", 30);
        String jsonStringParam = dtoToJsonString(member);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/members/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonStringParam))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("admin@google.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(30))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/members/create 요청 시 회원의 이메일형식을 지켜야 한다.")
    public void createMemberTest2() throws Exception{
        MemberRequestDto member = new MemberRequestDto("admingoogle.com", "admin", 30);
        String jsonStringParam = dtoToJsonString(member);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/members/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonStringParam))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldErrors[0].field").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldErrors[0].message").value("이메일 형식을 확인 해 주세요."))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/members/create 요청 시 회원의 이메일형식을 지켜야 하고, 이름은 필수 입력이다.")
    public void createMemberTest3() throws Exception{
        MemberRequestDto member = new MemberRequestDto("admingoogle.com", "", 30);
        String jsonStringParam = dtoToJsonString(member);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/members/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonStringParam))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldErrors[0].field").value("name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldErrors[0].message").value("이름을 입력 해 주세요."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldErrors[1].field").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldErrors[1].message").value("이메일 형식을 확인 해 주세요."))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    private <T> String dtoToJsonString(T dto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);

        return jsonString;
    }
}