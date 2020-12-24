package net.aydini.common.validation;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public interface Validator<T> {

    public boolean isValid(T t);
}
