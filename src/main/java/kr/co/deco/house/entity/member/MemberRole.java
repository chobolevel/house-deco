package kr.co.deco.house.entity.member;

import kr.co.deco.house.entity.Base;
import kr.co.deco.house.enums.member.MemberRoleCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRole extends Base {

    private String id;
    private MemberRoleCode code;
    private String name;

}
