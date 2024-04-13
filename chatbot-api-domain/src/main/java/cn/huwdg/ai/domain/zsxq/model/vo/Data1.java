package cn.huwdg.ai.domain.zsxq.model.vo;

import java.util.List;


public class Data1 {
    private Usage usage;
    private int created;
    private String model;
    private String id;
    private List<Choices> choices;

    public Data1() {
    }

    public Data1(Usage usage, int created, String model, String id, List<Choices> choices) {
        this.usage = usage;
        this.created = created;
        this.model = model;
        this.id = id;
        this.choices = choices;
    }

    /**
     * 获取
     * @return usage
     */
    public Usage getUsage() {
        return usage;
    }

    /**
     * 设置
     * @param usage
     */
    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    /**
     * 获取
     * @return created
     */
    public int getCreated() {
        return created;
    }

    /**
     * 设置
     * @param created
     */
    public void setCreated(int created) {
        this.created = created;
    }

    /**
     * 获取
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return choices
     */
    public List<Choices> getChoices() {
        return choices;
    }

    /**
     * 设置
     * @param choices
     */
    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public String toString() {
        return "Data{usage = " + usage + ", created = " + created + ", model = " + model + ", id = " + id + ", choices = " + choices + "}";
    }
}
