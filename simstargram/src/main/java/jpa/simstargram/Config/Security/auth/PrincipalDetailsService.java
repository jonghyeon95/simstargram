package jpa.simstargram.Config.Security.auth;

import jpa.simstargram.Entity.Member;
import jpa.simstargram.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> findMember = memberRepository.findByUsername(username);

        if (findMember.isPresent()) {
            return new PrincipalDetail(findMember.get());
        } else {
            throw new UsernameNotFoundException("로그인 실패");
        }
    }
}
