package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IMemberService;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IMemberService memberService;

    //产品添加
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

    //查询全部产品
    @RequestMapping("/findAll.do")
    @RolesAllowed("admin")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList", ps);
        mv.setViewName("product-list1");
        return mv;

    }
    @RequestMapping("findId.do")
    public ModelAndView findId() throws Exception {
        ModelAndView mv = new ModelAndView();

        //          调用 product  service 层的查询所有订单 方法  返回所有订单信息
        List<Product> productList = productService.findAll();
//          调用 member service 层 查询所有会员信息，
        List<Member> memberList     = memberService.findAll();

        mv.addObject("productList",productList);
        mv.addObject("memberList",memberList);
//        System.out.println(productList);
//        System.out.println(memberList);
        //        转发到orders-add.jsp页面
        mv.setViewName("orders-add");
        return mv;
    }

    @RequestMapping("findById.do")
    @ResponseBody
    public Product findById(@RequestParam(name = "productId",required = true) String productId) throws Exception {
        Product byId = productService.findById(productId);
        System.out.println(byId);
        return  byId;
    }
}
