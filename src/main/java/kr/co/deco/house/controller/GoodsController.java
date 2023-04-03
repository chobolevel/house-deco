package kr.co.deco.house.controller;

import kr.co.deco.house.entity.goods.Goods;
import kr.co.deco.house.exception.ApiException;
import kr.co.deco.house.service.goods.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("goods")
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping("list")
    public String goodsList(Model model) throws ApiException {
        model.addAttribute("list", goodsService.findAll(Goods.builder().build()));
        return "/goods/list";
    }

    @GetMapping("{id}")
    public String goodsDetail(@PathVariable String id, Model model) throws ApiException {
        model.addAttribute("goods", goodsService.findOne(Goods.builder().id(id).build()));
        return "/goods/detail";
    }

    @PostMapping("")
    public String createGoods(Goods goods) throws ApiException {
        goodsService.create(goods);
        return "redirect:/";
    }

    @PutMapping("{id}")
    public String updateGoods(@PathVariable String id, Goods goods) throws ApiException {
        goodsService.update(goods);
        return "redirect:/";
    }

    @DeleteMapping("{id}")
    public String deleteGoods(@PathVariable String id) throws ApiException {
        goodsService.delete(Goods.builder().id(id).build());
        return "redirect:/";
    }

}
