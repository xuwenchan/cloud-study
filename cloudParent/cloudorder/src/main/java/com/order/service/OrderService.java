package com.order.service;

import com.cloud.Model.Orders;
import com.cloud.util.Response;

public interface OrderService {

    Response<Boolean> updateOrder(Orders orders);

    Response<Boolean> insertOrder(Orders orders);
}
