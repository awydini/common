package net.aydini.common.spring;

import net.aydini.common.mapper.AbstractEntityMapper;
import net.aydini.common.mapper.AbstractMapper;
import net.aydini.common.mapper.Mapper;
import net.aydini.common.mapper.MappingMode;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 *         Jul 9, 2020
 */

public final class SpringAwareEntityMapper extends AbstractEntityMapper
{
    private final BeanUtil beanUtil;

    public SpringAwareEntityMapper(BeanUtil beanUtil)
    {
        this.beanUtil=beanUtil;
    }


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
