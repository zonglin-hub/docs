### 1. 初始化 ES 创建索引

```java
package com.example.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
  ES配置

  @author liuzonglin
 * @date 2022/10/09
 */

/**
 * 配置配置类注解
 * @author liuzonglin
 */
@Configuration
public class ElasticsearchConfig {

    /**
     * 配置 Bean
     * spring <beans id="restHighLevelClient" class="RestHighLevelClient"/>
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        // 集群配置多个 new HttpHost("localhost", 9201, "http")
                        new HttpHost("192.168.1.102", 9200, "http")));
    }
}
```

### 2. 创建 查询 删除 （索引 文档）

```java
package com.example.es.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户
 *
 * @author liuzonglin
 * @date 2022/10/09
 */

/**
 * Component 注入 spring 中
 * AllArgsConstructor 有参
 * NoArgsConstructor 无参
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Integer age;
}
```

```java
package com.example.es.utis;

/**
 * elasticsearch 工具栏
 *
 * @author liuzonglin
 * @date 2022/10/09
 */
public class ElasticsearchUtils {
    public final static String ES_INDEX = "liuzonglin_index";

}
```

```java
package com.example.es;

import com.alibaba.fastjson.JSON;
import com.example.es.pojo.User;
import com.example.es.utis.ElasticsearchUtils;
import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class EsApplicationTests {

    /**
     * 客户端
     * Autowired 默认选择他的一个类型
     * client 不是 RestHighLevelClient 的默认类型 名字问题，如果需要这样设置需要加 @Qualifier("restHighLevelClient")
     */
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    /**
     * 创建索引
     */
    @SneakyThrows
    @Test
    void createIndex() {
        // 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("liuzonglin_index");
        // 执行索引请求 indicesClient 请求后获得响应
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response);

    }

    /**
     * 查询索引
     */
    @SneakyThrows
    @Test
    void queryIndex() {
        // 获取索引请求
        GetIndexRequest request = new GetIndexRequest("liuzonglin_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    /**
     * 删除索引
     */
    @Test
    void deleteIndex() throws IOException {
        // 获取删除索引请求
        DeleteIndexRequest request = new DeleteIndexRequest("liuzonglin_index2");
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());

    }

    /**
     * 创建文档
     */
    @SneakyThrows
    @Test
    void createDocument() {
        // 创建对象
        User user = new User("liuzonglin", 26);
        // 创建请求
        IndexRequest request = new IndexRequest("liuzonglin_index");
        // 设置规则
        request.id("1");
        // 设置过期时间 1s
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        // 放入请求数据 json
        // 引入 str 转 josn 的依赖
        request.source(JSON.toJSONString(user), XContentType.JSON);
        // 客户端发送请求, 获取相应结果
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);
        System.out.println(index.toString());
        System.out.println(index.status());
    }

    /**
     * 得到文档
     * get/liuzonglin/_doc/1
     */
    @SneakyThrows
    @Test
    void getDocument() {
        // 获取请求
        GetRequest getRequest = new GetRequest("liuzonglin_index", "1");
        // 不获取返回的 _source 的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 获取文件信息
     */
    @SneakyThrows
    @Test
    void getDocumentInformation() {
        GetRequest getRequest = new GetRequest("liuzonglin_index", "1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        // 打印文档
        System.out.println(getResponse.getSourceAsString());
        System.out.println(getResponse.getSource());
        System.out.println(getRequest.version());
    }

    /**
     * 更新文档
     */
    @SneakyThrows
    @Test
    void updateDocument() {
        UpdateRequest updateRequest = new UpdateRequest("liuzonglin_index", "1");
        updateRequest.timeout("1s");
        User user = new User("liuzonglin1", 27);
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update);
    }

    /**
     * 删除文档
     */
    @SneakyThrows
    @Test
    void deleteDocument() {
        DeleteRequest deleteRequest = new DeleteRequest("liuzonglin_index", "1");
        deleteRequest.timeout("1s");
        DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete);
    }

    /**
     * 批量插入
     */// 批量插入数据
    @SneakyThrows
    @Test
    void bulkInsert() {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("100s");
        ArrayList<User> list = new ArrayList<User>() {
            {
                this.add(new User("liuzonglin1", 1));
                this.add(new User("liuzonglin2", 2));
                this.add(new User("liuzonglin3", 3));
                this.add(new User("liuzonglin4", 4));
                this.add(new User("liuzonglin5", 5));
                this.add(new User("liuzonglin6", 6));
                this.add(new User("liuzonglin7", 7));
                this.add(new User("liuzonglin8", 8));
                this.add(new User("liuzonglin9", 9));
                this.add(new User("liuzonglin10", 10));
            }
        };
        int size = list.size();
        for (int i = 0; i < size; i++) {
            bulkRequest.add(
                    new IndexRequest("liuzonglin_index")
                    .id(" " + (i + 1))
                    .source(JSON.toJSONString(list.get(i)),XContentType.JSON));
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        // 是否失败
        System.out.println(bulk.hasFailures());
    }

    /**
     * 获取文件信息
     */
    @SneakyThrows
    @Test
    void batchQuery() {
        // 1. 搜索请求
        SearchRequest searchRequest = new SearchRequest(ElasticsearchUtils.ES_INDEX);
        // 2. 搜索条件构建
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // QueryBuilders.termQuery(); 快速查询精确匹配
        // QueryBuilders.matchAllQuery(); 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "liuzonglin");
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 放入请求
        searchRequest.source(searchSourceBuilder);
        // 执行请求
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(search.getHits()));
        for (SearchHit documentFields : search.getHits().getHits()) {
            System.out.println(documentFields);
        }
    }
}
```
