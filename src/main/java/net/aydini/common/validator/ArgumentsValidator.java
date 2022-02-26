package net.aydini.common.validator;

import net.aydini.common.exception.ValidationException;

/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Jan 14, 2021
 */
public abstract class ArgumentsValidator<T> implements Validator<T> {

    public void validate(T... arguments) throws ValidationException
    {
        for(T t : arguments)
            validate(t);
    }
}
