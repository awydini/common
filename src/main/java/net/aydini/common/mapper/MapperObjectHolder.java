package net.aydini.common.mapper;


/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */
@Deprecated
public class MapperObjectHolder<S,T,M> {

    private S source;

    private Class<T> targetClass;

    private T target;

    private MappingMode<M> mappingMode;

    private boolean ignoreError =false;

    public S getSource() {
        return source;
    }

    public void setSource(S source) {
        this.source = source;
    }

    public MappingMode<M> getMappingMode() {
        return mappingMode;
    }

    public void setMappingMode(MappingMode<M> mappingMode) {
        this.mappingMode = mappingMode;
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public boolean isIgnoreError() {
        return ignoreError;
    }

    public void setIgnoreError(boolean ignoreError) {
        this.ignoreError = ignoreError;
    }

    public MapperObjectHolder(S source, Class<T> targetClass, MappingMode<M> mappingMode) {
        this.source = source;
        this.targetClass = targetClass;
        this.mappingMode = mappingMode;
    }

    public T getTarget() throws IllegalAccessException, InstantiationException {
        if(target == null)
            this.target =  targetClass.newInstance();
        return  target;
    }
    public Class<?> getSourceClass()
    {
        return source.getClass();
    }
}
