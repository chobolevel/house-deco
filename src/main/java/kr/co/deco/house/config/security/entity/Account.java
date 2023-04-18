package kr.co.deco.house.config.security.entity;

import kr.co.deco.house.entity.member.Member;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class Account extends User {

    private Member member;

    public Account(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
    }
}
