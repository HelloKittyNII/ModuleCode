package com.wzj.design;

import com.wzj.design.observe.FirstStudent;
import com.wzj.design.observe.SecondStudent;
import com.wzj.design.subject.TeacherSubject;
import com.wzj.design.vo.NotifyHomework;

/**
 * Created by wzj on 2016/11/21.
 */
public class Main
{
    public static void main(String[] args)
    {
        FirstStudent firstStudent = new FirstStudent();
        SecondStudent secondStudent = new SecondStudent();

        TeacherSubject subject = new TeacherSubject();
        NotifyHomework homework = new NotifyHomework("1-10","2-3");

        //add observer to subject
        subject.addObserver(firstStudent);
        subject.addObserver(secondStudent);

        //target changed，notify observers
        subject.setNotifyHomeworkContent(homework);
    }
}
