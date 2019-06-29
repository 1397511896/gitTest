package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.ExportDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ExportServiceImpl implements IExportService {

    @Autowired
    private ExportDao exportDao;

    @Override
    public List<Orders> findByArgs(String orderStatusId, String startTime, String endTime) {
        if ( orderStatusId.equals("")){
            orderStatusId = null;
        }
        if (startTime.equals("")){
            startTime = null;
        }
        if (endTime.equals("")){
            endTime = null;
        }

        return exportDao.findByArgs(orderStatusId,startTime,endTime);
    }
}
