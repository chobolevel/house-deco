package kr.co.deco.house.enums.member;

import kr.co.deco.house.enums.EnumBase;

public enum MemberLoginType implements EnumBase {
    DEFAULT("기본 로그인"),
    KAKAO("카카오 로그인"),
    NAVER("네이버 로그인")
    ;

    private final String name;

    MemberLoginType(String name) {
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
