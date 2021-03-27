package net.aydini.common.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 * Mar 26, 2021
 */

@Deprecated
public class ListMapper<I,O> 
{

    private final AbstractEntityMapper entityMapper;
    
    public ListMapper(AbstractEntityMapper entityMapper)
    {
        this.entityMapper = entityMapper;
    }


    public List<O> map(List<I> input,Class<O> outputClass)
    {
        if(input == null )
            return null;
        return input.parallelStream().map(item->entityMapper.map(item, outputClass)).collect(Collectors.toList());
    }
    
}
