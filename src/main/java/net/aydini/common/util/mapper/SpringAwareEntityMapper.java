package net.aydini.common.util.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import net.aydini.common.util.spring.BeanUtil;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 *         Jul 9, 2020
 */
public final class SpringAwareEntityMapper extends AbstractEntityMapper
{

    @Autowired
    private BeanUtil beanUtil;

    @Override
    protected <I> Mapper<I, ?> getMapper(Class<? extends Mapper<I, ?>> clazz) throws IllegalAccessException, InstantiationException
    {
        return beanUtil.getApplicationContext().getBean(clazz);
    }

    @Override
    protected <I, M> Mapper<I, ?> getAbstractMapper(Class<? extends Mapper<I, ?>> clazz, MappingMode<M> mappingMode)
            throws IllegalAccessException, InstantiationException
    {
        if (!beanUtil.isPrototypeComponent(clazz))
            throw new IllegalArgumentException(clazz.getSimpleName() + " should be a prototype scoped component");
        AbstractMapper<I, ?, M> abstractMapper  = (AbstractMapper<I, ?, M>) beanUtil.getApplicationContext().getBean(clazz);
        abstractMapper.setMappingMode(mappingMode);
        return abstractMapper;
    }

}
