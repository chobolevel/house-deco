package kr.co.deco.house.controller;

import kr.co.deco.house.entity.member.Member;
import kr.co.deco.house.exception.ApiException;
import kr.co.deco.house.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("signup")
    public String signup() {
        return "/common/signup";
    }

    @PostMapping("signup")
    public String signup(Member member) throws ApiException {
        memberService.create(member);
        return "/common/signin";
    }

    @GetMapping("signin")
    public String signin() {
        return "/common/signin";
    }

}
