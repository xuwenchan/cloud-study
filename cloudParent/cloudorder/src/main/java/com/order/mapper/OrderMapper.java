package com.order.mapper;

import com.cloud.Model.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    Integer updateOrder(Orders orders);

    Integer insertOrder(Orders orders);
}
