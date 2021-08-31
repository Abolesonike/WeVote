package com.example.wv.controller;

import com.example.wv.entity.Content;
import com.example.wv.entity.User;
import com.example.wv.service.ContentService;
import com.example.wv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ContentService contentService;

    @CrossOrigin
    @PostMapping ("/api/getLoginUser")
    public User getLoginUser(@RequestBody User user){
        return userService.getByName(user.getUsername());
    }

    @CrossOrigin
    @PostMapping("/api/isVoted")
    public Boolean isVoted(@RequestBody HashMap<String, Integer> id){
        int voteId = id.get("voteId"); // 帖子id
        int userId = id.get("userId"); // 当前用户id
        boolean voted = false; // 当前用户是否对该帖子进行了投票
        // 查找帖子信息
        Content vote = contentService.findContentById(voteId);
        // 帖子已投票的用户列表
        if(vote.getVoted_user() != null){
            String[] voted_user = vote.getVoted_user().split(",");
            // 检查列表是否有该用户
            for( String userid : voted_user ){
                if(userId == Integer.parseInt(userid)){
                    voted = true;
                    break;
                }
            }
            return voted;
        }
        return false;

    }

}


