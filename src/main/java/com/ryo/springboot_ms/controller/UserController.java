package com.ryo.springboot_ms.controller;

import com.ryo.springboot_ms.model.MiaoshaUser;
import com.ryo.springboot_ms.redis.RedisService;
import com.ryo.springboot_ms.result.Result;
import com.ryo.springboot_ms.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    MiaoshaUserService userService;
	
	@Autowired
    RedisService redisService;
	
    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }
    
}
