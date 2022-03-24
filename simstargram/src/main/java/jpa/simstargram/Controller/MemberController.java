package jpa.simstargram.Controller;

import jpa.simstargram.DTO.Member.JoinMemberDto;
import jpa.simstargram.Service.MemberService;
import jpa.simstargram.Utils.ResultCode.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static jpa.simstargram.Utils.ResultCode.ResultCode.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {

        return "Member/login";
    }

    @GetMapping("/join")
    public String joinForm(JoinMemberDto dto, Model model) {

        model.addAttribute("joinMemberDto", new JoinMemberDto());

        return "Member/join";
    }

    @PostMapping("/join")
    public String join(@Valid JoinMemberDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "Member/join";
        }

        ResultCode resultCode = memberService.join(dto);

        if (resultCode == PASSWORD_INCONSISTENCY) {
            bindingResult.addError(new FieldError("joinMemberDto", "password2", PASSWORD_INCONSISTENCY.getMessage()));
            return "Member/join";
        } else if (resultCode == DUPLICATED_USERNAME) {
            bindingResult.addError(new FieldError("joinMemberDto", "username", DUPLICATED_USERNAME.getMessage()));
            return "Member/join";
        } else if (resultCode == DUPLICATED_NICKNAME) {
            bindingResult.addError(new FieldError("joinMemberDto", "nickname", DUPLICATED_NICKNAME.getMessage()));
            return "Member/join";
        } else {
            return "redirect:/";
        }
    }
}
