package com.example.wv.mapper;

import com.example.wv.entity.Replay;
import com.example.wv.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReplayMapper {
    /**
     * 查找所有回复
     */
    List<Replay> findAllReplay();

    /**
     * 通过id查找
     * int id：id
     */
    Replay findById(int id);


    /**
     * 通过类型查找
     * int type：类型
     */
    List<Replay> findByReplayType(int type);

    /**
     * 通过belong和类型查找帖子找
     * int  id：用户id
     */
    List<Replay> findByReplayTypeAndBelong(int replayType, int belong);

    /**
     * 通过belong和类型查找帖子找
     * int  id：用户id
     */
    List<Replay> findBy1or2AndBelong(int belong);


    /**
     * 插入一个回复
     * @param replay 传入一个用户对象
     * @return 是否成功
     */
    Boolean insertOneReplay(Replay replay);

    Boolean replayLikeAdd(int id);
}
