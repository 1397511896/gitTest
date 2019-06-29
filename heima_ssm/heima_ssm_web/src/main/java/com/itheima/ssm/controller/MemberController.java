package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Traveller;
import com.itheima.ssm.service.IMemberService;
import com.itheima.ssm.service.ITravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private IMemberService memberService;

    @Autowired
    private ITravellerService travellerService;

    @RequestMapping("/findById.do")
    @ResponseBody
    public List<Traveller> findById(@RequestParam(name = "memberId",required = true) String memberId) throws Exception {
        return  travellerService.findByMemberId(memberId);

    }
}
