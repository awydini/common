package net.aydini.common.mapper;

import net.aydini.common.exception.MapperException;
import net.aydini.common.reflection.FieldWarehouse;

import java.lang.reflect.Field;
import java.util.Set;

/**
 *
 * @Author  Aydin Nasrollahpour
 *
 * Nov 15, 2020
 */
@Deprecated
public class SimpleEntityMapper  extends AbstractEntityMapper{


    @Override
    public <T, S> T map(S source, Class<T> targetClass) {
        return map(source, targetClass,null);
    }

    @Override
    public <T, S, M> T map(S source, Class<T> targetClass, MappingMode<M> mode) {
        return map(new MapperObjectHolder<S, T, M>(source, targetClass, mode));
    }


    public <T, S, M> T map(MapperObjectHolder<S, T, M> objectHolder)
    {
        try
        {
            objectHolder.setIgnoreError(true);
            Set<Field> targetFields = FieldWarehouse.getClassFields(objectHolder.getTargetClass());
            for (Field field : targetFields)
            {
              setNotAnnotatedFieldValueToObject(objectHolder, field);
            }
            return objectHolder.getTarget();
        }
        catch (Exception e)
        {
            throw new MapperException(e.getMessage(),e);
        }
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
