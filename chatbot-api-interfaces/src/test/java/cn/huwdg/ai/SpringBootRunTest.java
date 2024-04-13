package cn.huwdg.ai;

import cn.huwdg.ai.domain.zsxq.IZsxqApi;
import cn.huwdg.ai.domain.zsxq.Izhipuquery;
import cn.huwdg.ai.domain.zsxq.model.aggregrate.UnAnsweredQuestionsAggregates;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.fine_turning.QueryPersonalFineTuningJobApiResponse;
import com.zhipu.oapi.service.v4.fine_turning.QueryPersonalFineTuningJobRequest;
import com.zhipu.oapi.service.v4.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.huwdg.ai.domain.zsxq.Izhipuquery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.zhipu.oapi.demo.V4OkHttpClientTest.mapStreamToAccumulator;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootRunTest {
    private static final String API_KEY = "e6592c3bef8f90fc093180857352428a";
    private static final String API_SECRET = "NU8SeQAl1nANYxJw";
    private static final ClientV4 client = new ClientV4.Builder(API_KEY,API_SECRET).build();

    private static final ObjectMapper mapper = defaultObjectMapper();

    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.addMixIn(ChatFunction.class, ChatFunctionMixIn.class);
        mapper.addMixIn(ChatCompletionRequest.class, ChatCompletionRequestMixIn.class);
        mapper.addMixIn(ChatFunctionCall.class, ChatFunctionCallMixIn.class);
        return mapper;
    }

    // 请自定义自己的业务id
    private static final String requestIdTemplate = "mycompany-%d";

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Autowired
    private IZsxqApi zsxqApi;

    @Autowired
    private Izhipuquery zhipuquery;
    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnswerQuestionsTopicId(groupId,cookie);
        log.info("测试结果：{}", JSON.toJSONString((unAnsweredQuestionsAggregates)));
        QueryPersonalFineTuningJobRequest queryPersonalFineTuningJobRequest = new QueryPersonalFineTuningJobRequest();
        queryPersonalFineTuningJobRequest.setLimit(1);
        QueryPersonalFineTuningJobApiResponse queryPersonalFineTuningJobApiResponse = client.queryPersonalFineTuningJobs(queryPersonalFineTuningJobRequest);
        System.out.println("model output:" + JSON.toJSONString(queryPersonalFineTuningJobApiResponse));



    }



    @Test
    public void openaitest(){
        String response = zhipuquery.doChatGpt("请说一下什么是冒泡排序");
        log.info(response);
    }


}


