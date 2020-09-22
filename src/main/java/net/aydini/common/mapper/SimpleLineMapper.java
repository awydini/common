package net.aydini.common.mapper;

import net.aydini.common.string.SimpleTokenizer;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
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
