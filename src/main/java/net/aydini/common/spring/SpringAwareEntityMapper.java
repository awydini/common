package net.aydini.common.spring;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import net.aydini.common.mapper.AbstractEntityMapper;
import net.aydini.common.mapper.AbstractMapper;
import net.aydini.common.mapper.Mapper;
import net.aydini.common.mapper.MappingMode;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 *         Jul 9, 2020
 */

public final class SpringAwareEntityMapper extends AbstractEntityMapper
{

//    private final static Logger logger = Logger.getLogger(SpringAwareEntityMapper.class);
 


    @Override
    protected <I> Mapper<I, ?> getMapper(Class<? extends Mapper<I, ?>> clazz) throws IllegalAccessException, InstantiationException
    {
        try 
        {
            return ApplicationContextHolder.getApplicationContext().getBean(clazz);
        }
        catch(NoSuchBeanDefinitionException e)
        {
//            logger.debug(ReflectionUtil.getBeanNameByClass(clazz) + " not found  ");
//            logger.warn("trying to instantiate " + clazz.getSimpleName() + " out of spring context ");
        }
        return clazz.newInstance();
        
    }

    @Override
    protected <I, M> Mapper<I, ?> getAbstractMapper(Class<? extends Mapper<I, ?>> clazz, MappingMode<M> mappingMode)
            throws IllegalAccessException, InstantiationException
    {
        if (!BeanUtil.isPrototypeComponent(clazz))
            throw new IllegalArgumentException(clazz.getSimpleName() + " should be a prototype scoped component");
        AbstractMapper<I, ?, M> abstractMapper  = (AbstractMapper<I, ?, M>) ApplicationContextHolder.getApplicationContext().getBean(clazz);
        abstractMapper.setMappingMode(mappingMode);
        return abstractMapper;
    }

}
