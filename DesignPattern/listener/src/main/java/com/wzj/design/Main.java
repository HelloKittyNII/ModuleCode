package com.wzj.design;

import com.wzj.design.event.HitEvent;
import com.wzj.design.listener.HitListener;
import com.wzj.design.source.Desk;

import java.util.Date;

/**
 * Created by wzj on 2016/11/20.
 */
public class Main
{
    public static void main(String[] args)
    {
        Desk desk = new Desk();

        //注册hit事件
        desk.addHitListener(new HitListener()
        {
            public void handleEvent(HitEvent event)
            {
                System.out.println(new Date() + " hit desk");
            }
        });

        //触发hit事件
        desk.notifyHitListeners();
    }
}
