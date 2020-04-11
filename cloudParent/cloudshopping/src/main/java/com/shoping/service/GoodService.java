package com.shoping.service;

import com.cloud.Model.Goods;
import com.cloud.util.Response;

import java.util.List;

public interface GoodService {
    Response<Boolean> updateGoods(Goods goods);

    Response<Boolean> insertGoods(Goods goods);

    Response<Boolean> shopping(Goods goods);

    Response<List<Goods>> queryAllStock();
}
