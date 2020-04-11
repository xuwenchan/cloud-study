package com.shoping.config;

import com.cloud.Model.Goods;
import com.cloud.util.Response;
import com.shoping.service.GoodService;
import com.userModule.util.MyException;
import com.userModule.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* 系统启动完成之后立即处理的事情
*
* */

@Component
@Order(value = 1) //其中@Order注解来指定方法执行的顺序，也可通过实现org.springframework.core.Ordered接口并重写getOrder()方法来实现：

public class InitConfig implements ApplicationRunner {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    GoodService GoodService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initGoodStockToRedis();
    }

    public void initGoodStockToRedis(){
        Response<List<Goods>> reponse=GoodService.queryAllStock();
        if(reponse.isSuccess()){
            for(Goods goods:reponse.getResult()){
                try {
                    redisUtil.setString(goods.getId()+"",goods.getStock()+"");
                } catch (MyException e) {
                    System.out.println("添加货物库存到redis失败，货物id："+goods.getId());
                }
            }
        }

    }
}
