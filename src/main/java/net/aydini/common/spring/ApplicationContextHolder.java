package net.aydini.common.spring;

import org.springframework.context.ApplicationContext;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *         Mar 26, 2021
 */
public class ApplicationContextHolder
{

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext)
    {
        if (ApplicationContextHolder.applicationContext == null)
        {
            synchronized (ApplicationContextHolder.applicationContext)
            {
                if (applicationContext == null) ApplicationContextHolder.applicationContext = applicationContext;
            }
        }

    }

}
