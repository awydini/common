package net.aydini.common.mapper;


/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */
@Deprecated
public abstract class AbstractMapper<I,O,M> implements Mapper<I,O>{

    protected MappingMode<M> mappingMode;

    public  void setMappingMode(MappingMode<M> mappingMode) {
        this.mappingMode = mappingMode;
    }
}