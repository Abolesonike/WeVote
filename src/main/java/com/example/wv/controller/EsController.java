package com.example.wv.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wv.uitls.ElasticsearchUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class EsController {
    /**
     * 测试索引
     */
    private String indexName = "content";

    /**
     * 类型
     */
    private String esType = "content_doc";

    /**
     * 创建索引
     * http://127.0.0.1:8080/es/createIndex
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createIndex")
    public String createIndex(HttpServletRequest request, HttpServletResponse response) {
        if (!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        } else {
            return "索引已经存在";
        }
        return "索引创建成功";
    }

    /**
     * 查询日期范围数据
     *
     * @return
     */
    @RequestMapping("/queryDateRangeData")
    public String queryDateRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("id").from("1")
                .to("50"));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询数据
     * 模糊查询
     *
     * @return
     */
    @CrossOrigin
    @GetMapping("/api/es/queryMatchData/{keyword}")
    public String queryMatchData(@PathVariable("keyword") String keyword) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolean matchPhrase = false;
        if (matchPhrase == Boolean.TRUE) {
            //不进行分词搜索
            boolQuery.must(QueryBuilders.matchPhraseQuery("content", keyword));
        } else {
            boolQuery.must(QueryBuilders.matchQuery("content", keyword));
        }
        List<Map<String, Object>> list = ElasticsearchUtil.
                searchListData(indexName, esType, boolQuery, 10, "content,title", null, "content");
        return JSONObject.toJSONString(list);
    }

    @CrossOrigin
    @GetMapping("/api/es/queryMultiMatchData/{keyword}")
    public String queryMultiMatchData(@PathVariable("keyword") String keyword){
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword,
                "content", "title");
        List<Map<String, Object>> list = ElasticsearchUtil.
                searchListData(indexName, esType, queryBuilder, 10, "content,title", null, "content,title");
        return JSONObject.toJSONString(list);
    }



}
