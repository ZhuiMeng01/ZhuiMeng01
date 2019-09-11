package com.imooc.miaosha.controller;

import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/demo")
@Controller
public class simplecontroller {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping ("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "xixi");
        return "hello";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String>  hello() {
        return Result.success("hello,meimei");
        //return new Result(0,"success","hello,meng");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String>  helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
//        return new Result(500601,"session111");
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User>  dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean>  dbTx() {
        userService.tx();
        return Result.success(true);
    }



    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user  = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById, ""+1, user);//UserKey:id1
        return Result.success(true);
    }


}
