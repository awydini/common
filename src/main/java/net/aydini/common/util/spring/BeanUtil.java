package net.aydini.common.util.spring;

import net.aydini.common.util.string.Stringutil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Jul 9, 2020
 */

@Component
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
