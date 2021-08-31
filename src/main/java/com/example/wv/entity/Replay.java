package com.example.wv.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
@Document(indexName = "activity", type = "doc", shards = 6, replicas = 3)
public class Replay {
    private int id;
    private int fromUserId;
    private int toUserId;
    private String content;
    private int likeCount;
    private int replayType;
    private int belong;
    private Date createTime;
    private String formatCreateTime;
    // 发送用户消息
    private User fromUserInfo;
    // 回复用户消息
    private User toUserInfo;
    // 该条回复的回复
    private List<Replay> replay2replay;

}
