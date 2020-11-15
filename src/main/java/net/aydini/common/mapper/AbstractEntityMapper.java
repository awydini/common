package net.aydini.common.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import net.aydini.common.exception.MapperException;
import net.aydini.common.reflection.ReflectionUtil;
import net.aydini.common.reflection.FieldWarehouse;
import net.aydini.common.model.annotation.Mappable;
import net.aydini.common.model.annotation.MappedField;

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

    public <T, S> List<T> mapList(List<S> sourceList, Class<T> targetClass)
    {
        return sourceList.stream().map(item->map(item,targetClass,null)).collect(Collectors.toList());
    }

    public <T, S, M> T map(S source, Class<T> targetClass, MappingMode<M> mode)
    {
        return map(new MapperObjectHolder<S, T, M>(source, targetClass, mode));
    }


    public <T, S, M> List<T> mapList(List<S> sourceList, Class<T> targetClass, MappingMode<M> mode)
    {
        return sourceList.stream().map(source-> map(new MapperObjectHolder<S, T, M>(source, targetClass, mode))).collect(Collectors.toList());
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

    protected <T, S, M> void setNotAnnotatedFieldValueToObject(MapperObjectHolder<S, T, M> objectHolder, Field field)
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

    protected  <T, S, M> void setAnnotatedFieldValueToObject(MapperObjectHolder<S, T, M> objectHolder, Field annotatedField)
    {
        try
        {
            MappedField mappedField = annotatedField.getAnnotation(MappedField.class);

            Field sourceField = ReflectionUtil.findClassFieldByFieldName(objectHolder.getSourceClass(), mappedField.fieldName());
            Object value = null;
            if (mappedField.isCustom())
                value = convertValue((Class<Mapper<S, ?>>) mappedField.mapperClass(), objectHolder.getSource(),objectHolder.getMappingMode());
            else if (sourceField != null)
            {
                Object sourceValue = ReflectionUtil.getFieldValueFromObject(sourceField, objectHolder.getSource());
                value = getTargetFieldValue(sourceValue, annotatedField);
            }
            if(mappedField.isCustom() ==false && !mappedField.mapperClass().equals(Mapper.class))
                value = convertValue((Class<Mapper<Object, ?>>) mappedField.mapperClass(), value,objectHolder.getMappingMode());


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
        if (ReflectionUtil.isSimpleType((targetField.getType())) && source != null)
            targetFieldValue = mapCompositeField(source, targetField.getType());
        else targetFieldValue = source;
        return targetFieldValue;
    }

    private Object mapCompositeField(Object source, Class<?> targetClass)
    {
        return map(source, targetClass);
    }

    private <T, S, M, O> Object convertValue(Class<? extends Mapper<S, ?>> clazz,S source ,MappingMode<M> mappingMode)
    {
        if (!isMapper(clazz)) throw new MapperException("converter class should implment " + Mapper.class.getName());
        if (isConditionalMapper(clazz)) return ((ConditionalMapper<S, ?, M>) getConverterClass(clazz, mappingMode))
                .map(source, mappingMode);

        return getConverterClass(clazz, mappingMode).map(source);
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
