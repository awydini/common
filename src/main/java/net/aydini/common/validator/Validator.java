package net.aydini.common.validator;

import net.aydini.common.exception.ValidationException;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public interface Validator<T> {

    public void validate(T t) throws ValidationException;
}
