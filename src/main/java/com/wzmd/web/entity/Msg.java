package com.wzmd.web.entity;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 12:24 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class Msg {
    private String title;
    private String content;
    private String extraInfo;

    public Msg() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Msg(String title, String content, String extraInfo) {
        this.title = title;
        this.content = content;
        this.extraInfo = extraInfo;
    }
}
