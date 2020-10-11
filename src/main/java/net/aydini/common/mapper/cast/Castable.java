package net.aydini.common.mapper.cast;

/**
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 * 11.10.20
 **/
public interface Castable<S,T> {

    public T cast(S s) throws ClassCastException;
}
