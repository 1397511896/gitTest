package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IMemberDao;
import com.itheima.ssm.domain.Member;
import com.itheima.ssm.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements IMemberService {


    @Autowired
    private IMemberDao memberDao;

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Override
    public Member  findById(String memberId) throws Exception {
        return memberDao.findById(memberId);
    }
}
