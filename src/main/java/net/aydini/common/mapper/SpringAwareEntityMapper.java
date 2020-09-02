package net.aydini.common.mapper;

import net.aydini.common.util.spring.BeanUtil;

import org.springframework.stereotype.Component;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 *         Jul 9, 2020
 */
@Component
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
