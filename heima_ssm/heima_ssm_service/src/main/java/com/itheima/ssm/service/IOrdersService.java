package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Userinput;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrdersService {


    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String ordersId) throws Exception;

    void save(String productId, String memberId, String[] travellerNames, Orders orders);

    List<Orders> findByOrders(Userinput userinput);

}
