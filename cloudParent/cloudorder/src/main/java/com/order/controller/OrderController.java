package com.order.controller;


import com.cloud.Model.Orders;
import com.cloud.util.Response;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public Response<Boolean> editOrder(Orders orders){
        if(orders.getId()!=null){
            return orderService.updateOrder(orders);
        }else{
            return orderService.insertOrder(orders);
        }

    }
}
