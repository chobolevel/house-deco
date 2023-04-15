package kr.co.deco.house.entity.member;

import kr.co.deco.house.entity.Base;
import kr.co.deco.house.enums.member.MemberLoginType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends Base {

    private String id;
    private String username;
    private String password;
    private MemberLoginType loginType;
    private String name;
    private String email;
    private String mobile;
    private String address;

}
