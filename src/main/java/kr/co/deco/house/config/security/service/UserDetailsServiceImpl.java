package kr.co.deco.house.config.security.service;

import kr.co.deco.house.config.security.entity.User;
import kr.co.deco.house.entity.member.Member;
import kr.co.deco.house.enums.common.ApiExceptionType;
import kr.co.deco.house.exception.ApiException;
import kr.co.deco.house.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberMapper.findOne(Member.builder().username(username).build());

        if(findMember == null) {
            throw new UsernameNotFoundException("회원 정보를 찾을 수 없습니다.");
        }

        return new User(findMember);
    }
}
