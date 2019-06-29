package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExportDao {

    @Select({"<script>","select * from orders","where 1=1",
            "<when test='orderStatusId !=null'>","and orderStatus = #{orderStatusId}",
            "</when>",
            "<when test='startTime !=null'>","and ordertime &gt; #{startTime}",
            "</when>",
            "<when test='endTime!=null'>","and ordertime &lt;  #{endTime}",
            "</when>",
//            "order by ordertime desc ",
            "</script>"})
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderTimeStr",property = "orderTimeStr"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "orderStatusStr",property = "orderStatusStr"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "payTypeStr",property = "payTypeStr"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productid",property = "product",javaType = Product.class,
            one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")
            )

    })
    List<Orders> findByArgs(@Param("orderStatusId") String orderStatusId, @Param("startTime") String startTime,@Param("endTime") String endTime);
}
