package net.aydini.common.util.mapper;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Jul 9, 2020
 */


public final class EntityMapper extends AbstractEntityMapper
{

    @Override
    protected <I> Mapper<I, ?> getMapper(Class<? extends Mapper<I, ?>> clazz) throws IllegalAccessException, InstantiationException
    {
        return clazz.newInstance();
    }

    @Override
    protected <I, M> Mapper<I, ?> getAbstractMapper(Class<? extends Mapper<I, ?>> clazz, MappingMode<M> mappingMode)
            throws IllegalAccessException, InstantiationException
    {
        AbstractMapper<I, ?, M> abstractMapper = (AbstractMapper<I, ?, M>) clazz.newInstance();
        abstractMapper.setMappingMode(mappingMode);
        return abstractMapper;
    }

    


}
