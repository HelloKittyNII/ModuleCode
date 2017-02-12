package com.wzj.design;

import com.wzj.design.factory.AnimalFactory;

/**
 * Created by wzj on 2016/11/23.
 */
public class FactoryMain
{
    public static void main(String[] args)
    {
        AnimalFactory.getCat();
        AnimalFactory.getDog();
    }
}
