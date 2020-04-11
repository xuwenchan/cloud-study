package com.shoping.controller;

import com.cloud.Model.Goods;
import com.cloud.util.Response;
import com.google.common.util.concurrent.RateLimiter;
import com.shoping.service.GoodService;
import com.userModule.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/goods")
public class GoodsController {

    @Autowired
    GoodService goodService;

    @Autowired
    RedisUtil redisUtil;

    //Guava令牌桶：每秒放行10个请求，接口限流
    RateLimiter rateLimiter = RateLimiter.create(10);

    @RequestMapping(value="/editgoods",method = RequestMethod.POST)
    public Response<Boolean> editGoods(Goods goods){
        if(goods.getId()!=null){
            return goodService.updateGoods(goods);
        }else{
            return goodService.insertGoods(goods);
        }
    }

    @RequestMapping(value="/shopping",method = RequestMethod.POST)
    public Response<Boolean> shopingGoods(Goods goods){
        return goodService.shopping(goods);
    }


    /**
     * 做描述关注两个点
     * 1，关注货物的库存
     * 2，关注接口的并发量
     * @param gid
     * @return
     */
    @RequestMapping(value="/miaosha",method = RequestMethod.POST)
    public Response<Boolean> miaosha(@RequestParam(value = "gid") Integer gid){
        Response<Boolean> response=new Response<Boolean>();
        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            response.setMessage("你被限流");
            return response;
        }
        if((long)redisUtil.getString(gid+"")>0){//查看是否有库存
            //含有购买的库存量就递减库存
            try{
                redisUtil.descrease(gid+"",1L);
                //生成订单，放入消息队列中，

            }catch(Exception e){

            }

        }
        return response;
    }

}
