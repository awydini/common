package net.aydini.common.mapper;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 *         Jul 8, 2020
 */

public interface Mapper<I, O>
{
    O map(I input);
}
