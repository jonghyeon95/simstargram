package jpa.simstargram.Service;

import jpa.simstargram.Entity.Member;
import jpa.simstargram.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }
}
