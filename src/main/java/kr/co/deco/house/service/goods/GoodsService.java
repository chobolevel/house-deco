package kr.co.deco.house.service.goods;

import kr.co.deco.house.entity.goods.Goods;
import kr.co.deco.house.exception.ApiException;

import java.util.List;

public interface GoodsService {

    List<Goods> findAll(Goods goods) throws ApiException;

    Goods findOne(Goods goods) throws ApiException;

    void create(Goods goods) throws ApiException;

    void update(Goods goods) throws ApiException;

    void delete(Goods goods) throws ApiException;

}
