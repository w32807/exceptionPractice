package com.study.exceptionpractice.web.responseDto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {

    private String itemName;
    private int price;
    
}
