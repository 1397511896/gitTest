package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Userinput;
import com.itheima.ssm.service.IOrdersService;
import com.sun.glass.ui.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //查询全部订单---未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        List<Orders> ordersList = ordersService.findAll();
//        mv.addObject("ordersList", ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//    }

    @RequestMapping("/findAll.do")
    @Secured("ROLE_user")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        System.out.println(orders.getMember());
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        System.out.println(orders);
        return mv;
    }
    @RequestMapping("/save.do")  //  产品表
    public String save( @RequestParam(name = "productId",required = true) String productId,
//                                  会员表
                        @RequestParam(name = "memberId",required = true) String memberId,
//                               旅客表
                        @RequestParam(name = "travellerNames",required = true) String[] travellerNames ,
                        //      订单表
                        Orders orders){

        System.out.println(orders.getPayType());

        ordersService.save(productId,memberId,travellerNames,orders);

        return "redirect:/orders/findAll.do";
    }

    @RequestMapping("/findByOrders.do")
    public ModelAndView findByOrders(Userinput userInput){

        ModelAndView mv = new ModelAndView();
        System.out.println(userInput);
//        System.out.println(userinput);
        List<Orders> ordersList = ordersService.findByOrders(userInput);
        System.out.println(ordersList);
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }


}
