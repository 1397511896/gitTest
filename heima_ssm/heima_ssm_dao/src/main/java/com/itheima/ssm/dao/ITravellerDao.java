package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId) throws Exception;

    @Select("select * from traveller where id in(select travellerid from member_traveller where memberid = #{MemberId})")
    public  List<Traveller> findByMemberId(String MemberId);

    //   添加  会员跟旅客   的关联关系
    @Insert("insert into member_traveller values(#{memberId},#{travellerId})")
    void addTraveller(@Param("memberId") String memberId,@Param("travellerId") String travellerId );
}
