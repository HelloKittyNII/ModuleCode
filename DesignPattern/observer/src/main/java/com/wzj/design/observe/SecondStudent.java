package com.wzj.design.observe;

import com.wzj.design.subject.TeacherSubject;
import com.wzj.design.vo.NotifyHomework;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by wzj on 2016/11/21.
 */
public class SecondStudent implements Observer
{
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg)
    {
        //拉模式
//        NotifyHomework homework = (NotifyHomework) arg;
//        System.out.println("SecondStudent" + homework.toString());

        //推模式
        TeacherSubject subject = (TeacherSubject) o;
        System.out.println("push SecondStudent"
                + subject.getNotifyHomeworkContent().toString());
    }
}
