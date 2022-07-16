package com.study.exceptionpractice.web.controller.Item;

import com.study.exceptionpractice.web.requestDto.item.ItemRequestDto;
import com.study.exceptionpractice.web.responseDto.item.ItemResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ItemApiController {

    @PostMapping("/api/items/create")
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody @Valid ItemRequestDto itemRequestDto){
        String itemName = itemRequestDto.getItemName();
        int price = itemRequestDto.getPrice();

        // 실제 상품 등록 로직

        // 상품 등록을 완료하면 완료한 상품의 정보를 ItemResponseDto에 담아 반환한다.
        ItemResponseDto itemResponseDto = new ItemResponseDto(itemName, price);

        return new ResponseEntity<>(itemResponseDto, HttpStatus.OK);
    }
}
