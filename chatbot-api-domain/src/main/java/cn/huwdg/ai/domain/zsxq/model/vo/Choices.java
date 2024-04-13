package cn.huwdg.ai.domain.zsxq.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Choices {
    @JsonProperty("finish_reason")
    private String finishReason;
    private int index;
    private Message message;

}
