package kr.co.deco.house.config.security;

import kr.co.deco.house.entity.member.Member;
import kr.co.deco.house.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.findOne(Member.builder().username(username).build());
        if(member == null) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }
        return member;
    }
}
