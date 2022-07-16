package com.study.exceptionpractice.web.controller.member;

import com.study.exceptionpractice.web.requestDto.member.MemberRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MemberController {

    @GetMapping("/members")
    public String members(){
        // createMember 메소드에서 등록로직 처리 후 redirect를 위해 구색맞추기로 추가
        return "/member/members";
    }

    @GetMapping("/members/create")
    public String createMemberForm(Model model){
        MemberRequestDto member = (MemberRequestDto) model.getAttribute("member");

        if(member == null){
            model.addAttribute("member", new MemberRequestDto());
        }

        return "/member/createMemberForm";
    }

    @PostMapping("/members/create")
    public String createMember(@Valid @ModelAttribute("member") MemberRequestDto member
            , BindingResult bindingResult, RedirectAttributes rttr){
        if(bindingResult.hasErrors()){
            rttr.addFlashAttribute("org.springframework.validation.BindingResult.member", bindingResult);
            rttr.addFlashAttribute("member", member);
            return "redirect:/members/create";
        }
        // 실제 회원 등록 로직
        return "redirect:/members";
    }

}

