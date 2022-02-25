package net.aydini.common.validator;

import net.aydini.common.exception.ValidationException;

/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Jan 14, 2021
 */
public interface ArgumentValidator<T>
{

    public boolean isValid(T ... t) throws ValidationException;
}
