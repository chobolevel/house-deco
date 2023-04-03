package kr.co.deco.house.enums.member;

import kr.co.deco.house.enums.EnumBase;

public enum MemberRoleType implements EnumBase {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");
    ;

    private final String name;

    MemberRoleType(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
