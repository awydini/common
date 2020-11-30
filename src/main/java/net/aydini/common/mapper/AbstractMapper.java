package net.aydini.common.mapper;


/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Jul 9, 2020
 */
public abstract class AbstractMapper<I,O,M> implements Mapper<I,O>{

    protected MappingMode<M> mappingMode;

    public  void setMappingMode(MappingMode<M> mappingMode) {
        this.mappingMode = mappingMode;
    }
}