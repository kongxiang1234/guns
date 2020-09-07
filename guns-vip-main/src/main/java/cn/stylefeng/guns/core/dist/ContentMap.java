package cn.stylefeng.guns.core.dist;

import cn.stylefeng.guns.base.dict.AbstractDictMap;

public class ContentMap  extends AbstractDictMap {

    @Override
    public void init() {
        put("title", "标题");
        put("content", "内容");
        put("status", "发布状态");
        put("type", "信息状态");
    }

    @Override
    protected void initBeWrapped() {

    }
}
