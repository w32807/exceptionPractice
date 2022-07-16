package com.study.exceptionpractice.web.controller.member;

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
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/members/create 요청 시 회원을 등록한다.")
    public void createMemberTest1() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/members/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("email", "admin@google.com")
                .param("name", "admin")
                .param("age", "30"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/members/create 요청 시 회원의 이메일형식을 지켜야 한다.")
    public void createMemberTest2() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/members/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("email", "이메일 형식이 아닌 문자열!")
                .param("name", "admin")
                .param("age", "30"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/members/create 요청 시 회원의 이메일은 필수다")
    public void createMemberTest3() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/members/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("email", "")
                .param("name", "admin")
                .param("age", "30"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/members/create 요청 시 회원의 나이는 0~200 사이로 입력해야 한다.")
    public void createMemberTest4() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/members/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("email", "admin@google.com")
                .param("name", "admin")
                .param("age", "201"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

}