package com.example.wv.entity;

import lombok.Data;

import java.util.Date;


@Data
public class Content {
    int id;
    String title;
    int post_user_id;
    String content;
    Date post_time;
    int status;
    String choose;
    String vote_number;
    String voted_user;
}
