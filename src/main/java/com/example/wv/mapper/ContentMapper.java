package com.example.wv.mapper;

import com.example.wv.entity.Content;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {
    /**
     * 查找所有帖子
     */
    List<Content> findAllContent();

    /**
     * 通过id查询帖子
     */
    Content findContentById(int id);

    /**
     *  插入一条
     */
    boolean insertOne(Content content);

    /**
     * 添加投票用户
     */
    boolean addVoteUser(int userId, int id);

    /**
     * 更新投票用户
     */
    boolean updateVoteNumber(String vote_number, int id);

    /**
     * 更新投票用户
     */
    String findVoteNumberById(int id);
}
