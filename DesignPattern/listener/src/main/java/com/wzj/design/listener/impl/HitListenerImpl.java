package com.wzj.design.listener.impl;

import com.wzj.design.event.HitEvent;
import com.wzj.design.listener.HitListener;

/**
 * Created by wzj on 2016/11/20.
 * 监听器实现类
 */
public class HitListenerImpl implements HitListener
{
    /**
     * 监听器实现方法，回调
     * @param event 事件
     */
    public void handleEvent(HitEvent event)
    {
        //回调事件方法
        event.hit();
    }
}
