package com.example.wv.controller;


import com.example.wv.entity.User;
import com.example.wv.result.Result;
import com.example.wv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;


@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    /**
     * 用户登录接口
     * @param requestUser 接收到的用户对象
     * @param session session用于拦截
     * @return 成功返回给前端200，失败返回400
     */
    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        ArrayList<Integer> arrayList = new ArrayList<>();
        Collections.sort(arrayList);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            session.setAttribute("user", user);
            System.out.println("用户" + user.getUsername() + "登陆成功");
            return new Result(200);
        }
    }

    @CrossOrigin
    @PostMapping("/api/signIn")
    public Result signIn(@RequestBody User requestUser){
        System.out.println(requestUser);
        String username = requestUser.getUsername();
        requestUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        if (userService.getByName(username) != null){
            // 用户名不能重复
            return new Result(300);
        }else{
            if(userService.insertOneUser(requestUser)){
                return new Result(200);
            }else {
                // 插入失败
                return new Result(300);
            }
        }
    }

}
