package kr.co.deco.house.mapper.member;

import kr.co.deco.house.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> findAll(Member member);

    Member findOne(Member member);

    void create(Member member);

    void update(Member member);

    void delete(Member member);

}
