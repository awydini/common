package net.aydini.common.mapper;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */

@Deprecated
public final class EntityMapper extends AbstractEntityMapper
{

    private EntityMapper(){}
    private static  EntityMapper entityMapper;

    public static EntityMapper getInstance()
    {
        if(entityMapper == null)
            entityMapper = new EntityMapper();
        return entityMapper;
    }

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
