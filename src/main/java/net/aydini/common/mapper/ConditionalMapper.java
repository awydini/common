package net.aydini.common.mapper;
/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Jul 9, 2020
 */
public interface  ConditionalMapper<I,O,M> extends Mapper<I,O>  {

    public O map(I i , MappingMode<M> mode);

}
