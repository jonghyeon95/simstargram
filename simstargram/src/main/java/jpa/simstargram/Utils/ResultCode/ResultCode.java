package jpa.simstargram.Utils.ResultCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static jpa.simstargram.Utils.ResultCode.ResultCode.Category.*;

@Getter
@RequiredArgsConstructor
public enum ResultCode {

    OK(0, NORMAL, "Ok"),
    PASSWORD_INCONSISTENCY(10001, CLIENT_SIDE, "패스워드가 일치하지 않습니다."),
    DUPLICATED_USERNAME(10002, CLIENT_SIDE, "이미 존재하는 아이디 입니다."),
    DUPLICATED_NICKNAME(10003, CLIENT_SIDE, "이미 존재하는 닉네임 입니다.")

    ;

    private final Integer code;
    private final Category category;
    private final String message;


    public enum Category{
        NORMAL, CLIENT_SIDE, SERVER_SIDE
    }
}
