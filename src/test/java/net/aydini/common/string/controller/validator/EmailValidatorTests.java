package net.aydini.common.string.controller.validator;

import net.aydini.common.exception.ValidationException;
import net.aydini.common.validator.EmailValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class EmailValidatorTests {

    private EmailValidator emailValidator;

    @BeforeEach
    public void init()
    {
        emailValidator = new EmailValidator();
    }

    @Test
    public void testValidate()
    {
        emailValidator.validate("hi@aydini.net");
    }

    @Test
    public void shouldThrowValidationException()
    {
       assertThrows(ValidationException.class,()-> emailValidator.validate("123456"));
        assertThrows(ValidationException.class,()-> emailValidator.validate("hi@aydini"));
        assertThrows(ValidationException.class,()-> emailValidator.validate("hi.net"));
    }
}
