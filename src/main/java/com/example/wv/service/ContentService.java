package com.example.wv.service;

import com.example.wv.entity.Content;
import com.example.wv.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Autowired
    ContentMapper contentMapper;

    /**
     * 查询所有帖子
     * @return 帖子对象的集合
     */
    public List<Content> findAllContent(){
        return contentMapper.findAllContent();
    }

    /**
     * 通过id查询帖子
     * @param id 要查询的id
     * @return 帖子对象
     */
    public Content findContentById(int id){
        return contentMapper.findContentById(id);
    }

    /**
     * 插入一条数据
     * @param content 插入数据
     * @return 是否成功
     */
    public Boolean insertOne(Content content){
        return contentMapper.insertOne(content);
    }

    /**
     * 更新投票用户
     * @param userId 用户id
     * @param id 帖子id
     * @return 是否成功
     */
    public boolean addVoteUser(int userId, int id){
        return contentMapper.addVoteUser(userId, id);
    }

    private String findVoteNumberById(int id){
        return contentMapper.findVoteNumberById(id);
    }

    /**
     * 更新投票数量
     * @param voteNumber 新投票数
     * @param id 帖子id
     * @return 是否成功
     */
    public boolean updateVoteNumber(String voteNumber, int id){
        String[] voteList = voteNumber.split(",");
        String[] voteNumberOld = contentMapper.findVoteNumberById(id).split(",");
        StringBuilder voteNumberNew = new StringBuilder();
        int index = 0;
        for( String s : voteList){
            if(s.equals("true")){
                voteNumberNew.append(Integer.parseInt(voteNumberOld[index]) + 1).append(",");
            }else{
                voteNumberNew.append(voteNumberOld[index]).append(",");
            }
            index += 1;
        }

        return contentMapper.updateVoteNumber(voteNumberNew.toString(), id);
    }

}
