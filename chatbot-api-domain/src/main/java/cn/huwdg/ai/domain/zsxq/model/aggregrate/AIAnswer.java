package cn.huwdg.ai.domain.zsxq.model.aggregrate;


import cn.huwdg.ai.domain.zsxq.model.vo.Data1;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AIAnswer {
    private int code;
    private String msg;
    private boolean success;

    @JsonProperty("data")
    private Data1 data;
}
