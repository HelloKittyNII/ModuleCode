package com.wzj.design.factory;

import com.wzj.design.mode.Cat;
import com.wzj.design.mode.Dog;

/**
 * Created by wzj on 2016/11/23.
 */
public final class AnimalFactory
{
    /**
     * 私有的构造函数
     */
    private AnimalFactory()
    {

    }

    /**
     * get dog mode
     * @return Dog
     */
    public static Dog getDog()
    {
        return new Dog();
    }

    /**
     * get cat mode
     * @return Cat
     */
    public static Cat getCat()
    {
        return new Cat();
    }
}
