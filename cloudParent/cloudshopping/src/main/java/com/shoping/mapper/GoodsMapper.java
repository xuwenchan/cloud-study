package com.shoping.mapper;

import com.cloud.Model.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    Integer updateGoods(Goods goods);

    Integer insertGoods(Goods goods);

    Goods queryGoodsById(Goods goods);

    /**
     *
     * @param id 货物的id
     * @param i 购买的数量
     * @param stock 原库存数
     * @return
     */
    Integer reduceStock(@Param("id") Long id, @Param("count") Integer count, @Param("stock") Integer stock);

    /**
     * 获取所有的获取的库存数，系统启动成功后放入redis中
     * @return 所以的货物的库存信息
     */
    List<Goods> queryAllStock();
}
