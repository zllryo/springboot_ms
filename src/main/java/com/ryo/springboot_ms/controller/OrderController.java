package com.ryo.springboot_ms.controller;

import com.ryo.springboot_ms.model.MiaoshaUser;
import com.ryo.springboot_ms.model.OrderInfo;
import com.ryo.springboot_ms.redis.RedisService;
import com.ryo.springboot_ms.result.CodeMsg;
import com.ryo.springboot_ms.result.Result;
import com.ryo.springboot_ms.service.GoodsService;
import com.ryo.springboot_ms.service.MiaoshaUserService;
import com.ryo.springboot_ms.service.OrderService;
import com.ryo.springboot_ms.vo.GoodsVo;
import com.ryo.springboot_ms.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
									  @RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	OrderInfo order = orderService.getOrderById(orderId);
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	long goodsId = order.getGoodsId();
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }
    
}
