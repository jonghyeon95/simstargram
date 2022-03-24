package jpa.simstargram.Controller;

import jpa.simstargram.Entity.Member;
import jpa.simstargram.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {

        return "Member/login";
    }

    @GetMapping("/join")
    public String joinForm() {

        return "Member/join";
    }

    @PostMapping("/join")
    public String join(Member member) {

        memberService.join(member);

        return "redirect:/";
    }
}
