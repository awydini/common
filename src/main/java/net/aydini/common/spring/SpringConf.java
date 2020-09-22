package net.aydini.common.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Sep 4, 2020
 */

@Configuration
public class SpringConf {

    private ConfigurableApplicationContext configurableApplicationContext;

    SpringConf(ConfigurableApplicationContext configurableApplicationContext)
    {
        this.configurableApplicationContext=configurableApplicationContext;
    }
    @Bean
    public BeanUtil beanUtil()
    {
        return new BeanUtil(configurableApplicationContext);
    }

    @Bean
    public SpringAwareEntityMapper springAwareEntityMapper()
    {
        return new SpringAwareEntityMapper(beanUtil());
    }

}
