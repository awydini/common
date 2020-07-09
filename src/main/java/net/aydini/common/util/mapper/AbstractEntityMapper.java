package net.aydini.common.util.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;

import net.aydini.common.util.exception.MapperException;
import net.aydini.common.util.reflection.FieldWarehouse;
import net.aydini.common.util.reflection.ReflectionUtil;
import net.aydini.common.util.reflection.annotation.Mappable;
import net.aydini.common.util.reflection.annotation.MappedField;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 *         Jul 9, 2020
 */
public abstract class AbstractEntityMapper
{
    public <T, S> T map(S source, Class<T> targetClass)
    {
        return map(source, targetClass, null);
    }

    public <T, S, M> T map(S source, Class<T> targetClass, MappingMode<M> mode)
    {
        return map(new MapperObjectHolder<S, T, M>(source, targetClass, mode));
    }

    public <T, S, M> T map(MapperObjectHolder<S, T, M> mapperObjectHolder)
    {
        try
        {
            if (mapperObjectHolder.getSourceClass().isAnnotationPresent(Mappable.class)) mapAnnotatedFieldsOnly(mapperObjectHolder);
            else mapAllFields(mapperObjectHolder);
            return mapperObjectHolder.getTarget();
        }
        catch (Exception e)
        {
            throw new MapperException(e);
        }
    }

    private <T, S, M> void mapAllFields(MapperObjectHolder<S, T, M> objectHolder)
    {
        Set<Field> targetFields = FieldWarehouse.getClassFields(objectHolder.getTargetClass());
        for (Field field : targetFields)
        {
            if (field.isAnnotationPresent(MappedField.class)) setAnnotatedFieldValueToObject(objectHolder, field);
            else setNotAnnotatedFieldValueToObject(objectHolder, field);
        }
    }

    private <T, S, M> void mapAnnotatedFieldsOnly(MapperObjectHolder<S, T, M> objectHolder)
    {
        Set<Field> targetFields = FieldWarehouse.getAnnotatedClassFields(objectHolder.getTargetClass(), MappedField.class);
        for (Field field : targetFields)
        {
            setAnnotatedFieldValueToObject(objectHolder, field);
        }
    }

    private <T, S, M> void setNotAnnotatedFieldValueToObject(MapperObjectHolder<S, T, M> objectHolder, Field field)
    {
        try
        {
            Field sourceField = ReflectionUtil.findClassFieldByFieldName(objectHolder.getSourceClass(), field.getName());
            if (sourceField == null) return;
            Object sourceFieldValue = ReflectionUtil.getFieldValueFromObject(sourceField, objectHolder.getSource());
            Object targetFieldValue = getTargetFieldValue(sourceFieldValue, field);
            ReflectionUtil.setFieldValueToObject(field, objectHolder.getTarget(), targetFieldValue);
        }
        catch (Exception e)
        {
            throw new MapperException(e);
        }
    }

    private <T, S, M> void setAnnotatedFieldValueToObject(MapperObjectHolder<S, T, M> objectHolder, Field annotatedField)
    {
        try
        {
            MappedField mappedField = annotatedField.getAnnotation(MappedField.class);

            Field sourceField = ReflectionUtil.findClassFieldByFieldName(objectHolder.getSourceClass(), mappedField.fieldName());
            Object value = null;
            if (mappedField.isCustom()) value = convertValue((Class<Mapper<S, ?>>) mappedField.mapperClass(), objectHolder);
            else if (sourceField != null)
            {
                Object sourceValue = ReflectionUtil.getFieldValueFromObject(sourceField, objectHolder.getSource());
                value = getTargetFieldValue(sourceValue, annotatedField);
            }
            if (value == null) value = mappedField.ifNullValue().getValue();
            ReflectionUtil.setFieldValueToObject(annotatedField, objectHolder.getTarget(), value);
        }
        catch (Exception e)
        {
            throw new MapperException(e);
        }
    }

    private Object getTargetFieldValue(Object source, Field targetField)
    {
        Object targetFieldValue = null;
        if (ReflectionUtil.hasSuperClass((targetField.getType())) && source != null)
            targetFieldValue = mapCompositeField(source, targetField.getType());
        else targetFieldValue = source;
        return targetFieldValue;
    }

    private Object mapCompositeField(Object source, Class<?> targetClass)
    {
        return map(source, targetClass);
    }

    private <T, S, M, O> Object convertValue(Class<? extends Mapper<S, ?>> clazz, MapperObjectHolder<S, T, M> objectHolder)
    {
        if (clazz.isInterface() || clazz.isEnum()) throw new MapperException("Illegal converter class");
        if (!isMapper(clazz)) throw new MapperException("converter class should impleent " + Mapper.class.getName());
        if (isConditionalMapper(clazz)) return ((ConditionalMapper<S, ?, M>) getConverterClass(clazz, objectHolder.getMappingMode()))
                .map(objectHolder.getSource(), objectHolder.getMappingMode());

        return getConverterClass(clazz, objectHolder.getMappingMode()).map(objectHolder.getSource());
    }

    private <I, M> Mapper<I, ?> getConverterClass(Class<? extends Mapper<I, ?>> clazz, MappingMode<M> mappingMode)
    {
        try
        {
            if (isAbstractMapper(clazz)) return getAbstractMapper(clazz, mappingMode);
            return getMapper(clazz);
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            throw new MapperException("error creating bean " + clazz.getName(), e);
        }
    }

    protected abstract <I> Mapper<I, ?> getMapper(Class<? extends Mapper<I, ?>> clazz)
            throws IllegalAccessException, InstantiationException;

    protected abstract <I, M> Mapper<I, ?> getAbstractMapper(Class<? extends Mapper<I, ?>> clazz, MappingMode<M> mappingMode)
            throws IllegalAccessException, InstantiationException;

    private boolean isConditionalMapper(Class<?> clazz)
    {
        return ReflectionUtil.isClassContainsInterface(clazz, ConditionalMapper.class);
    }

    private boolean isMapper(Class<?> clazz)
    {
        return ReflectionUtil.isClassContainsInterface(clazz, Mapper.class);
    }

    private boolean isAbstractMapper(Class<?> clazz)
    {
        return Arrays.asList(clazz.getClasses()).contains(AbstractMapper.class);
    }

}
