package net.aydini.common.spring;

import net.aydini.common.string.Stringutil;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */

public class BeanUtil
{
    
    public static  boolean isPrototypeComponent(Class<?> clazz)
    {
        return ApplicationContextHolder.getApplicationContext().isPrototype(getBeanNameByClass(clazz));
    }

    public static boolean isSingletonComponent(Class<?> clazz)
    {
        return ApplicationContextHolder.getApplicationContext().isSingleton(getBeanNameByClass(clazz));
    }


    private static String getBeanNameByClass(Class<?> clazz)
    {
        return Stringutil.toLowerFirstLetter(clazz.getSimpleName());
    }

}
