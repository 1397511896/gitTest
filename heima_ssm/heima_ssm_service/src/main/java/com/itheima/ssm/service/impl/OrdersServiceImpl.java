package com.itheima.ssm.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IMemberDao;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.dao.ITravellerDao;
import com.itheima.ssm.domain.*;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;
    @Autowired
    private IProductDao productDao;
    @Autowired
    private ITravellerDao travellerDao;
    @Autowired
    private IMemberDao memberDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {

        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception{
        System.out.println(ordersId);
        return ordersDao.findById(ordersId);

    }

    @Override
    public void save(String productId, String memberId, String[] travellerNames, Orders orders) {
//          添加会员表，   把对应的旅客信息 添加进去
        Integer payType = orders.getPayType();
        if (payType == null || payType .equals("")){
            orders.setPayType(1);
        }

        orders.setPeopleCount(travellerNames.length);
        System.out.println(travellerNames.length);
//         添加orders 表    添加的  数据的时候 根据产品id 关联 产品
        orders.setProductId(productId);
//        添加会员表， 添加  数据的时候 根据 会员id 关联产品
        orders.setMemberId(memberId);
//        System.out.println(orders);

        ordersDao.addOrders(orders);

//        System.out.println(orders.getId()+"!!!!!!");

        for (String travellerId : travellerNames) {
            String orderId = orders.getId();
            System.out.println(orders);

            ordersDao.addTraveller(orderId,travellerId);

        }

    }

    @Override
    public List<Orders> findByOrders(Userinput userinput) {
       /* if (userinput.getEndTime() .equals("") || userinput.getEndTime() == null){
            userinput.setEndTime(null);
        }

        if (userinput.getStartTime() .equals("") || userinput.getStartTime() == null){
            userinput.setStartTime(null);
        }*/

        PageHelper.startPage(1,10);
        return ordersDao.findByOrders(userinput);
    }
}
