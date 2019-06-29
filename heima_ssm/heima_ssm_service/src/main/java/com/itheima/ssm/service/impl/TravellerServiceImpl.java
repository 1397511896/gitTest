package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.ITravellerDao;
import com.itheima.ssm.domain.Traveller;
import com.itheima.ssm.service.ITravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TravellerServiceImpl implements ITravellerService {

    @Autowired
    private ITravellerDao travellerDao;

    @Override
    public List<Traveller> findByMemberId(String memberId) {
        return travellerDao.findByMemberId(memberId);

    }
}
