package kr.co.deco.house.mapper.member;

import kr.co.deco.house.entity.member.MemberRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRoleMapper {

    List<MemberRole> findAll(MemberRole memberRole);

    MemberRole findOne(MemberRole memberRole);

    void create(MemberRole memberRole);

    void update(MemberRole memberRole);

    void delete(MemberRole memberRole);

}
