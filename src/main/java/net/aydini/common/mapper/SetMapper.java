package net.aydini.common.mapper;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @Author Aydin Nasrollahpour
 *
 *         Jul 8, 2020
 */

@Deprecated
public class SetMapper<I, O>
{
    private final AbstractEntityMapper entityMapper;

    public SetMapper(AbstractEntityMapper entityMapper)
    {
        this.entityMapper = entityMapper;
    }

    public Set<O> map(Set<I> input, Class<O> outputClass)
    {
        if (input == null) return null;
        return input.parallelStream().map(item -> entityMapper.map(item, outputClass)).collect(Collectors.toSet());
    }
}
