package net.aydini.common.mapper;
/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */
public interface  ConditionalMapper<I,O,M> extends Mapper<I,O>  {

    public O map(I i , MappingMode<M> mode);

}
