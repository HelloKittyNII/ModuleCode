package com.wzj.design.listener;

import com.wzj.design.event.HitEvent;

import java.util.EventListener;

/**
 * Created by wzj on 2016/11/20.
 * 监听器接口
 */
public interface HitListener extends EventListener
{
    //必须实现的接口
    public void handleEvent(HitEvent event);
}
