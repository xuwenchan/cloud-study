package com.order.service.impl;

import com.cloud.Model.Orders;
import com.cloud.util.Response;
import com.order.mapper.OrderMapper;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public Response<Boolean> updateOrder(Orders orders) {
        Response<Boolean> response=new Response<Boolean>();
        Integer count= orderMapper.updateOrder(orders);
        if(count>0){
            response.setSuccess(true);
        }else{
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    public Response<Boolean> insertOrder(Orders orders) {
        Response<Boolean> response=new Response<Boolean>();
        Integer count=orderMapper.insertOrder(orders);
        if(count>0){
            response.setSuccess(true);
        }else{
            response.setSuccess(false);
        }
        return response;
    }
}
