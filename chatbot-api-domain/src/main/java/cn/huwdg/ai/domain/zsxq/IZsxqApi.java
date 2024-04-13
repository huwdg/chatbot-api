package cn.huwdg.ai.domain.zsxq;

import cn.huwdg.ai.domain.zsxq.model.aggregrate.UnAnsweredQuestionsAggregates;

import java.io.IOException;

public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnswerQuestionsTopicId(String groupId, String cookie) throws IOException;


    boolean answer(String groupId,String cookie,String topicId,String text,boolean silenced) throws IOException;
}
