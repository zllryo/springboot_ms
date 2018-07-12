package com.ryo.springboot_ms.rabbitmq;

import com.ryo.springboot_ms.model.MiaoshaOrder;
import com.ryo.springboot_ms.model.MiaoshaUser;
import com.ryo.springboot_ms.redis.RedisService;
import com.ryo.springboot_ms.service.GoodsService;
import com.ryo.springboot_ms.service.MiaoshaService;
import com.ryo.springboot_ms.service.OrderService;
import com.ryo.springboot_ms.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MQReceiver {

		private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
		
		@Autowired
        RedisService redisService;
		
		@Autowired
        GoodsService goodsService;
		
		@Autowired
        OrderService orderService;
		
		@Autowired
        MiaoshaService miaoshaService;
		
		@RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
		public void receive(String message) {
			log.info("receive message:"+message);
			MiaoshaMessage mm  = RedisService.stringToBean(message, MiaoshaMessage.class);
			MiaoshaUser user = mm.getUser();
			long goodsId = mm.getGoodsId();
			
			GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
	    	int stock = goods.getStockCount();
	    	if(stock <= 0) {
	    		return;
	    	}
	    	//判断是否已经秒杀到了
	    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
	    	if(order != null) {
	    		return;
	    	}
	    	//减库存 下订单 写入秒杀订单
	    	miaoshaService.miaosha(user, goods);
		}

		
}
