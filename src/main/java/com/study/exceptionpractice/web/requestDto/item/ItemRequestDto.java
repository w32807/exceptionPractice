package com.study.exceptionpractice.web.requestDto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {

    @NotBlank(message = "상품명을 입력 해 주세요.")
    private String itemName;

    @Min(value = 0, message = "상품 금액을 확인 해 주세요")
    private int price;

}
