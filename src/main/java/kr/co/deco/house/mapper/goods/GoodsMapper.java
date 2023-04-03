package kr.co.deco.house.mapper.goods;

import kr.co.deco.house.entity.goods.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    List<Goods> findAll(Goods goods);

    Goods findOne(Goods goods);

    void create(Goods goods);

    void update(Goods goods);

    void delete(Goods goods);

}
