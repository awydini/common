package net.aydini.common.spring;

import net.aydini.common.string.Stringutil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */

public class BeanUtil
{

    private ConfigurableApplicationContext configurableApplicationContext;

    public BeanUtil(ConfigurableApplicationContext configurableApplicationContext)
    {
        this.configurableApplicationContext=configurableApplicationContext;
    }
    

    public ApplicationContext getApplicationContext()
    {
        return configurableApplicationContext;
    }
    
    
    public boolean isPrototypeComponent(Class<?> clazz)
    {
        return configurableApplicationContext.isPrototype(getBeanNameByClass(clazz));
    }

    public boolean isSingletonComponent(Class<?> clazz)
    {
        return configurableApplicationContext.isSingleton(getBeanNameByClass(clazz));
    }


    private String getBeanNameByClass(Class<?> clazz)
    {
        return Stringutil.toLowerFirstLetter(clazz.getSimpleName());
    }

}
