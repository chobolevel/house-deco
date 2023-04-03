package kr.co.deco.house.service.member.impl;

import kr.co.deco.house.entity.member.Member;
import kr.co.deco.house.enums.common.ApiExceptionType;
import kr.co.deco.house.enums.member.MemberRoleType;
import kr.co.deco.house.exception.ApiException;
import kr.co.deco.house.mapper.member.MemberMapper;
import kr.co.deco.house.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Member> findAll(Member member) throws ApiException {
        return memberMapper.findAll(member);
    }

    @Override
    public Member findOne(Member member) throws ApiException {
        return memberMapper.findOne(member);
    }

    @Override
    public void create(Member member) throws ApiException {
        if(member.getUsername() == null || member.getUsername().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "username");
        } else if(member.getPassword() == null || member.getPassword().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "password");
        } else if(member.getName() == null || member.getName().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "name");
        } else if(member.getEmail() == null || member.getEmail().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "email");
        } else if(member.getMobile() == null || member.getMobile().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "mobile");
        } else if(member.getAddress() == null || member.getAddress().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "address");
        }
        member.setId(UUID.randomUUID().toString());
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole(MemberRoleType.ROLE_ADMIN.getName());
        memberMapper.create(member);
    }

    @Override
    public void update(Member member) throws ApiException {
        if(member.getId() == null || member.getId().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "id");
        }
        memberMapper.update(member);
    }

    @Override
    public void delete(Member member) throws ApiException {
        if(member.getId() == null || member.getId().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "id");
        }
        memberMapper.delete(member);
    }
}
