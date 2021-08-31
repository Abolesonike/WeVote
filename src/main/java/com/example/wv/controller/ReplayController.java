package com.example.wv.controller;

import com.example.wv.entity.Content;
import com.example.wv.entity.Replay;
import com.example.wv.entity.User;
import com.example.wv.service.ReplayService;
import com.example.wv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.scanner.ScannerImpl;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ReplayController {
    @Autowired
    ReplayService replayService;

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/api/replaylsit/{id}")
    public Map<String,Object> list(@PathVariable("id") int id) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Map<String,Object> resultList = new HashMap<>();

        // 查帖子的回复
        List<Replay> replayList = replayService.findByReplayTypeAndBelong(0,id);

        for(Replay replay : replayList){
            replay.setFromUserInfo(userService.findUserById(replay.getFromUserId()));
            replay.setFormatCreateTime(format.format(replay.getCreateTime()));
            // 回复的回复
            List<Replay> replay2replayList = replayService.findBy1or2AndBelong(replay.getId());
            for(Replay replay2replay : replay2replayList){
                replay2replay.setFromUserInfo(userService.findUserById(replay2replay.getFromUserId()));
                replay2replay.setToUserInfo(userService.findUserById(replay2replay.getToUserId()));
                replay2replay.setFormatCreateTime(format.format(replay2replay.getCreateTime()));
            }
            replay.setReplay2replay(replay2replayList);
        }

        resultList.put("postReplay",replayList);
        return resultList;
    }

    @CrossOrigin
    @PostMapping("/api/sendReplay")
    @ResponseBody
    public boolean sendReplay(@RequestBody HashMap<String, String> data){
        Replay replay = new Replay();
        replay.setFromUserId(Integer.parseInt(data.get("fromUserId")));
        replay.setToUserId(Integer.parseInt(data.get("toUserId")));
        replay.setContent(data.get("content"));
        replay.setLikeCount(0);
        replay.setReplayType(Integer.parseInt(data.get("replayType")));
        replay.setBelong(Integer.parseInt(data.get("belong")));
        replay.setLikeCount(0);
        replay.setCreateTime(new Date());
        return replayService.insertOneReplay(replay);
    }

    @CrossOrigin
    @PostMapping("/api/replayLike")
    @ResponseBody
    public boolean replayLikeAdd(@RequestBody HashMap<String,Integer> id){
        System.out.println(id);
        return replayService.replayLikeAdd(id.get("id"));
    }

}
