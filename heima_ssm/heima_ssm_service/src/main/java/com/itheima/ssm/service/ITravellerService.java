package com.itheima.ssm.service;

import com.itheima.ssm.domain.Traveller;

import java.util.List;

public interface ITravellerService {
    List<Traveller> findByMemberId(String memberId);
}
