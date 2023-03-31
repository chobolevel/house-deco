package kr.co.deco.house.service.member;

import kr.co.deco.house.entity.member.Member;
import kr.co.deco.house.exception.ApiException;

import java.util.List;

public interface MemberService {

    List<Member> findAll(Member member) throws ApiException;

    Member findOne(Member member) throws ApiException;

    void create(Member member) throws ApiException;

    void update(Member member) throws ApiException;

    void delete(Member member) throws ApiException;

}
