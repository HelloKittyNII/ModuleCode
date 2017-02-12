package com.wzj.design.source;

import com.wzj.design.event.HitEvent;
import com.wzj.design.listener.HitListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzj on 2016/11/20.
 * 定义桌子实体对象
 */
public class Desk
{
    //监听自己的监听器队列
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    public Desk()
    {

    }

    /**
     * 注册hit监听器
     * @param hitListener hit监听器
     */
    public void addHitListener(HitListener hitListener)
    {
        hitListeners.add(hitListener);
    }

    /**
     * 通知所有的监听器
     */
    public void notifyHitListeners()
    {
        for (HitListener hitListener : hitListeners)
        {
            hitListener.handleEvent(new HitEvent(this));
        }
    }

}
