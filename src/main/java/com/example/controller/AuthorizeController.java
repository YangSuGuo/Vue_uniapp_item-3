package com.example.controller;

import com.example.entity.RestBean;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    @Resource
    AuthorizeService service;

    /**
     * 注册
     */
    @PostMapping("/register")
    public RestBean<String> registerUser(@Length(min = 2, max = 8) @RequestParam("username") String username,
                                         @Length(min = 6, max = 16) @RequestParam("password") String password,
                                         HttpSession session) {
        String s = service.validateAndRegister(username, password, session.getId());
        if (s == null)
            return RestBean.success("注册成功");
        else
            return RestBean.failure(400, s);
    }

    /**
     * 微信获取openid
     */
    @PostMapping("/wxuser/getOpenId")
    public RestBean<String> registerUser(@Length(min = 32, max = 32) @RequestParam("code") String code) {
        String openId = service.getUserOpenId(code);
        if (openId != null)
            return RestBean.success(openId);
        else
            return RestBean.failure(400);
    }
    /**
     * todo 获取文章内容（简）
     */

    /**
     * todo 获取文章正文 Post 文章id
     */
}
