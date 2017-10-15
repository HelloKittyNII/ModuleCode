package com.wzj.fuzzer.type;

/**
 * Created by wzj on 2017/10/14.
 */
public enum ProgressBarStatus
{
    /**
     * 显示
     */
    VISIBLE(0),

    /**
     * 隐藏
     */
    HIDE(1);

    /**
     * 显示状态
     */
    private int status;

    ProgressBarStatus(int status)
    {
        this.status = status;
    }

    /**
     * 获取状态
     * @return 状态
     */
    public int getStatus()
    {
        return status;
    }
}
