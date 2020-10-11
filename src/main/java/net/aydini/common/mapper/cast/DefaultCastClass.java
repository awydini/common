package net.aydini.common.mapper.cast;

/**
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 * 11.10.20
 **/

public class DefaultCastClass<S,T> implements Castable<S,T> {

    @Override
    public T cast(S s) throws ClassCastException {
        return (T)s;
    }
}
