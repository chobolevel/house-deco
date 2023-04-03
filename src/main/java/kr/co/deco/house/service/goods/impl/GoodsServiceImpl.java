package kr.co.deco.house.service.goods.impl;

import kr.co.deco.house.entity.goods.Goods;
import kr.co.deco.house.enums.common.ApiExceptionType;
import kr.co.deco.house.exception.ApiException;
import kr.co.deco.house.mapper.goods.GoodsMapper;
import kr.co.deco.house.service.goods.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private final GoodsMapper goodsMapper;

    @Override
    public List<Goods> findAll(Goods goods) throws ApiException {
        return goodsMapper.findAll(goods);
    }

    @Override
    public Goods findOne(Goods goods) throws ApiException {
        if(goods.getId() == null || goods.getId().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "id");
        }
        return goodsMapper.findOne(goods);
    }

    @Override
    public void create(Goods goods) throws ApiException {
        if(goods.getTitle() == null || goods.getTitle().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "title");
        }
        goods.setId(UUID.randomUUID().toString());
        goodsMapper.create(goods);
    }

    @Override
    public void update(Goods goods) throws ApiException {
        if(goods.getId() == null || goods.getId().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "id");
        }
        goodsMapper.update(goods);
    }

    @Override
    public void delete(Goods goods) throws ApiException {
        if(goods.getId() == null || goods.getId().trim().equals("")) {
            throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "id");
        }
        goodsMapper.delete(goods);
    }
}
