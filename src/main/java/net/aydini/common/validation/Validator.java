package net.aydini.common.validation;

import net.aydini.common.exception.ValidationException;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public interface Validator<T> {

    public boolean isValid(T t) throws ValidationException;
}
