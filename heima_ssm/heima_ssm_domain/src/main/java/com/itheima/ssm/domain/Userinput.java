package com.itheima.ssm.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Userinput {
    private  Integer orderStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "Userinput{" +
                "orderStatus=" + orderStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Userinput(Integer orderStatus, Date startTime, Date endTime) {
        this.orderStatus = orderStatus;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Userinput() {
    }


}
