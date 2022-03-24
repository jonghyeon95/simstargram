package jpa.simstargram.DTO.Member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class JoinMemberDto {

    @Email(message = "이메일 형식에 맞지 않습니다")
    @NotBlank(message = "필수 입력 값입니다")
    private String username;
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{3,20}$", message = "닉네임은 특수문자를 제외한 3~20자리여야 합니다")
    private String nickname;
    @NotBlank(message = "필수 입력 값입니다")
    private String name;
//    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{7,20}",
//            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{6,12}",
            message = "비밀번호는 영어와 숫자로 포함해서 6~12자의 비밀번호여야 합니다")
    private String password;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password2;
}
