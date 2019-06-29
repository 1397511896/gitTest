package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IMemberDao {

    @Select("select * from member where id  = #{id}")
    public Member  findById(String id) throws Exception;


    @Select("select * from member")
    List<Member> findAll();
}
