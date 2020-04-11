package com.shoping.service.impl;

import com.cloud.Model.Goods;
import com.cloud.Model.Orders;
import com.cloud.util.Response;
import com.order.mapper.OrderMapper;
import com.order.service.OrderService;
import com.shoping.mapper.GoodsMapper;
import com.shoping.service.GoodService;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    OrderService orderService;

    @Override
    public Response<Boolean> updateGoods(Goods goods) {
        Response<Boolean> response = new Response<Boolean>();
        Integer count = goodsMapper.updateGoods(goods);
        if (count != null && count > 0) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    public Response<Boolean> insertGoods(Goods goods) {
        Response<Boolean> response = new Response<Boolean>();
        Integer count = goodsMapper.insertGoods(goods);
        if (count != null && count > 0) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    @Hmily(confirmMethod = "confireShoping", cancelMethod = "cancelShoping")
    public Response<Boolean> shopping(Goods goods) {
        Response<Boolean> response = new Response<Boolean>();
        Goods gd1 = goodsMapper.queryGoodsById(goods);//从数据库查出当前获取的信息，
        if (gd1 != null && gd1.getStock() > 0) {//库存大于一才能下单，demo模拟一件一件的购买
            //库存减一
            Integer reduceFlag = goodsMapper.reduceStock(goods.getId(), 1, goods.getStock());
            if (reduceFlag > 0) {
                //生成订单
                Orders orders = new Orders();
                orders.setGid(goods.getId());
                orders.setUid(1L);
                Response<Boolean> orderResponse = orderService.insertOrder(orders);
                if (orderResponse.isSuccess()) {
                    response.setSuccess(true);
                } else {
                    response.setSuccess(false);
                }
            } else {
                response.setSuccess(false);
                response.setMessage("库存消减失败");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("库存不够");
        }
        return response;
    }


    public Response<Boolean> confireShoping(Goods goods) {
        Response<Boolean> response = new Response<Boolean>();
        return response;
    }

    public Response<Boolean> cancelShoping(Goods goods) {
        Response<Boolean> response = new Response<>();
        Integer count = goodsMapper.updateGoods(goods);
        if (count != null && count > 0) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    public Response<List<Goods>> queryAllStock() {
        Response<List<Goods>> response=new Response<List<Goods>>();
        List<Goods> list=goodsMapper.queryAllStock();
        return null;
    }

}
