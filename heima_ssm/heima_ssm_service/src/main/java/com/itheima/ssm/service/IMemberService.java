package com.itheima.ssm.service;

import com.itheima.ssm.domain.Member;


import java.util.List;

public interface IMemberService {

    List<Member> findAll();

    Member findById(String memberId) throws Exception;
}
