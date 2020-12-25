package net.aydini.common.mapper;

import net.aydini.common.string.SimpleTokenizer;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 9, 2020
 */

public class SimpleLineMapper<T> {

    private Mapper<SimpleTokenizer,T> mapper;

     public SimpleLineMapper(Mapper<SimpleTokenizer,T> mapper)
    {
        this.mapper= mapper;
    }

    public T map(SimpleTokenizer tokenizer)
    {
        return mapper.map(tokenizer);
    }

}
