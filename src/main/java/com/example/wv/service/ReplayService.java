package com.example.wv.service;

import com.example.wv.entity.Replay;
import com.example.wv.entity.User;
import com.example.wv.mapper.ReplayMapper;
import com.example.wv.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplayService {
    @Autowired
    ReplayMapper replayMapper;


    /**
     * 查询所有回复
     * @return 用户对象的集合
     */
    public List<Replay> findAllReplay(){
        return replayMapper.findAllReplay();
    }


    public Replay findById(int id) {
        return replayMapper.findById(id);
    }


    public List<Replay> findByReplayType(int type){
        return replayMapper.findByReplayType(type);
    }


    public List<Replay> findByReplayTypeAndBelong(int replayType, int belong) {
        return replayMapper.findByReplayTypeAndBelong(replayType, belong);
    }

    public List<Replay> findBy1or2AndBelong(int belong) {
        return replayMapper.findBy1or2AndBelong(belong);
    }



    public boolean insertOneReplay(Replay replay){
        return replayMapper.insertOneReplay(replay);
    }

    public boolean replayLikeAdd(int id){
        return replayMapper.replayLikeAdd(id);
    }


}
