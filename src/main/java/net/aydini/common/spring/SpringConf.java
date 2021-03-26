package net.aydini.common.spring;

import javax.annotation.PostConstruct;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @Author  Aydin Nasrollahpour
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
    @PostConstruct
    public void setApplicationContext()
    {
        ApplicationContextHolder.setApplicationContext(configurableApplicationContext);
    }

    @Bean
    public SpringAwareEntityMapper springAwareEntityMapper()
    {
        return new SpringAwareEntityMapper();
    }

}
