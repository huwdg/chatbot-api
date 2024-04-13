package cn.huwdg.ai.job;

import cn.huwdg.ai.domain.zsxq.IZsxqApi;
import cn.huwdg.ai.domain.zsxq.Izhipuquery;
import cn.huwdg.ai.domain.zsxq.model.aggregrate.UnAnsweredQuestionsAggregates;
import cn.huwdg.ai.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * 问题任务
 */

@EnableScheduling
@Configuration
@Slf4j
public class ChatbotSchedule {
    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private Izhipuquery izhipuquery;


    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Scheduled(cron = "0/30 * * * * ?")
    public void run(){
        try{
            if(new Random().nextBoolean()){
                log.info("随机打烊");
                return;
            }
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if(hour>24||hour<8){
                log.info("时间太晚了，请明天再来提问吧");
                return;
            }

            //1.检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnswerQuestionsTopicId(groupId, cookie);
            log.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if(topics == null || topics.isEmpty()){
                log.info("本次未查询到待回答问题");
                return;
            }
            //2.ai回答
            Topics topic = topics.get(0);
            String answer = izhipuquery.doChatGpt(topic.getQuestion().getText());
            //3.问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            log.info("编号：{} 问题：{} 状态：{}",topic.getTopic_id(),topic.getQuestion().getText(),answer,status);
        } catch (Exception e){
            log.info("知识星球回答异常");
        }
    }
}
