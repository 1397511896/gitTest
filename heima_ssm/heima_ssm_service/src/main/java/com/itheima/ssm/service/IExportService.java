package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

public interface IExportService {
    List<Orders> findByArgs(String orderStatusId, String startTime, String endTime);
}
