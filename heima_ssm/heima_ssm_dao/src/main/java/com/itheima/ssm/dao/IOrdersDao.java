package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.domain.Userinput;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),
    })
    public List<Orders> findAll() throws Exception;

    //多表操作
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId) throws Exception;


    @SelectKey(keyProperty = "id",before = true,resultType = String.class,statement = "select SYS_GUID() from dual")
    @Insert("insert into orders (id,peopleCount,ordernum,ordertime,orderdesc,paytype,orderstatus,productid,memberid) values(#{id},#{peopleCount},#{orderNum},#{orderTime},#{orderDesc},#{payType},#{orderStatus},#{productId},#{memberId})")
    void addOrders(Orders orders);

    @Insert("insert into order_traveller values(#{orderId},#{travellerId})")
    void addTraveller(@Param("orderId") String orderId, @Param("travellerId")String travellerId);
/*{"<script>" ,"select * from orders where 1 =1 ",
            "<if test = 'orderStatus != null  and orderStatus != \"\"' >","and orderstatus = #{orderStatus}","</if>",
            "<if test = 'startTime != null  and startTime != \"\"' >","and ordertime > #{startTime}","</if>",
            "<if test = 'endTime != null  and endTime != \"\"' >","and ordertime < #{endTime}","</if>",
            "</script>"}*/


    @Select({"<script>","select * from orders","where 1=1",
            "<when test='orderStatus !=null'>","and orderStatus = #{orderStatus}",
            "</when>",
            "<when test='startTime !=null'>","and ordertime &gt; #{startTime}",
            "</when>",
            "<when test='endTime!=null'>","and ordertime &lt;  #{endTime}",
            "</when>",
            "order by ordertime desc ",
            "</script>"}
    )
    List<Orders> findByOrders(Userinput userinput);
}
/*
* */
