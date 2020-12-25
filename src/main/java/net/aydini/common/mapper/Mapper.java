package net.aydini.common.mapper;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 *         Jul 8, 2020
 */

public interface Mapper<I, O>
{
    O map(I input);
}
