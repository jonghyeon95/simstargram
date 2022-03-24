package jpa.simstargram.Service;

import jpa.simstargram.DTO.Member.JoinMemberDto;
import jpa.simstargram.Entity.Member;
import jpa.simstargram.Repository.MemberRepository;
import jpa.simstargram.Utils.ResultCode.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static jpa.simstargram.Utils.ResultCode.ResultCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResultCode join(JoinMemberDto dto) {

        if (!dto.getPassword().equals(dto.getPassword2())) {
            return PASSWORD_INCONSISTENCY;
        }

        if (!validateDuplicationUsername(dto.getUsername())) {
            return DUPLICATED_USERNAME;
        }

        if (!validateDuplicationNickname(dto.getNickname())) {
            return DUPLICATED_NICKNAME;
        }

        String encodePassword = passwordEncoder.encode(dto.getPassword());

        Member member = Member.builder().username(dto.getUsername()).nickname(dto.getNickname()).name(dto.getName())
                .password(encodePassword).state(1).social(1).build();

        memberRepository.save(member);
        return OK;
    }

    private Boolean validateDuplicationUsername(String username) {
        Optional<Member> findMember = memberRepository.findByUsername(username);
        return findMember.isPresent() ? false : true;
    }

    private Boolean validateDuplicationNickname(String nickname) {
        Optional<Member> findMember = memberRepository.findByNickname(nickname);
        return findMember.isPresent() ? false : true;
    }
}
