package com.wzj.design.subject;

import com.wzj.design.vo.NotifyHomework;

import java.util.Observable;

/**
 * Created by wzj on 2016/11/21.
 */
public class TeacherSubject extends Observable
{
    private NotifyHomework notifyHomeworkContent;


    /**
     * 得到通知的内容
     * @return
     */
    public NotifyHomework getNotifyHomeworkContent()
    {
        return notifyHomeworkContent;
    }

    /**
     * 设置通知的内容
     * @param notifyHomeworkContent
     */
    public void setNotifyHomeworkContent(NotifyHomework notifyHomeworkContent)
    {
        this.notifyHomeworkContent = notifyHomeworkContent;

        setChanged();

        //通知观察者，拉模式
       // notifyObservers(notifyHomeworkContent);

        //通知观察者，推模式
        notifyObservers();
    }


}
