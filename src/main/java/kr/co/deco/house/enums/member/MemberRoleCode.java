package kr.co.deco.house.enums.member;

import kr.co.deco.house.enums.EnumBase;

public enum MemberRoleCode implements EnumBase {
    ROLE_SYSTEM_ADMIN("시스템 관리자"),
    ROLE_ADMIN("일반 관리자")
    ;

    private final String name;

    MemberRoleCode(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getName() {
        return name;
    }
}
