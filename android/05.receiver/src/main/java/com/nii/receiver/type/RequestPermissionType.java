package com.nii.receiver.type;

/**
 * Created by wzj on 2017/2/19.
 */
public interface RequestPermissionType
{
    /**
     * 请求打电话的权限码
     */
    int REQUEST_CODE_ASK_CALL_PHONE = 100;

    /**
     * 请求接收短信息
     */
    int REQUEST_CODE_ASK_RECEIVE_SMS = 101;

    /**
     * 请求读短信
     */
    int REQUEST_CODE_ASK_READ_SMS = 102;
}
