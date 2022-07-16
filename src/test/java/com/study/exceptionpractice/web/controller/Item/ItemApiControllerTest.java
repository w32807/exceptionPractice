package com.study.exceptionpractice.web.controller.Item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.exceptionpractice.web.requestDto.item.ItemRequestDto;
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
class ItemApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/items/create 요청 시 상품을 등록한다.")
    public void createItemTest1() throws Exception{
        ItemRequestDto Item = new ItemRequestDto("상품명", 10000);
        String jsonStringParam = dtoToJsonString(Item);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/items/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonStringParam))
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemName").value("상품명"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10000))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/items/create 요청 시 상품명은 필수입력이다.")
    public void createItemTest2() throws Exception{
        ItemRequestDto Item = new ItemRequestDto("", 10000);
        String jsonStringParam = dtoToJsonString(Item);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/items/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonStringParam))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldErrors[0].field").value("itemName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldErrors[0].message").value("상품명을 입력 해 주세요."))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    private <T> String dtoToJsonString(T dto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);

        return jsonString;
    }
}