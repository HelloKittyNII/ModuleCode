package com.wzj.design.event;

import java.util.EventObject;

/**
 * Created by wzj on 2016/11/20.
 * 事件
 */
public class HitEvent extends EventObject
{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public HitEvent(Object source)
    {
        super(source);
    }

    public void hit()
    {
        System.out.println("This is hit event");
    }
}
