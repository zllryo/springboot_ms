package com.ryo.springboot_ms.controller;

import com.ryo.springboot_ms.redis.RedisService;
import com.ryo.springboot_ms.service.MiaoshaUserService;
import com.ryo.springboot_ms.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ryo.springboot_ms.result.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/login")
@Controller
public class LoginController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public  String toLogin()
    {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        //登录
        String token = userService.login(response, loginVo);
        return Result.success(token);
    }
}
