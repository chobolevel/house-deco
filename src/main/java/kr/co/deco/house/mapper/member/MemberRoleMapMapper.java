package kr.co.deco.house.mapper.member;

import kr.co.deco.house.entity.member.MemberRoleMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRoleMapMapper {

    void create(MemberRoleMap memberRoleMap);

}
