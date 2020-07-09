package net.aydini.common.util.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    

    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }
    
    
    public boolean isPrototypeComponent(Class<?> clazz)
    {
        String[] beanNames = applicationContext.getBeanNamesForType(clazz);
        if (beanNames == null || beanNames.length == 0) return false;

        return applicationContext.getBeanFactory().getBeanDefinition(beanNames[0]).getScope()
                .equals(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
    }
    
    

}
