package com.nii.dialog.bean;

/**
 * Created by wzj on 2017/2/19.
 */
public class ItemBean
{
    private int imageId;
    private String message;

    public ItemBean(int imageId, String message)
    {
        this.imageId = imageId;
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public int getImageId()
    {
        return imageId;
    }

    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
