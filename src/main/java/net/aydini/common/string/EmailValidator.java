package net.aydini.common.string;

import net.aydini.common.validation.Validator;

import java.util.regex.Pattern;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public class EmailValidator implements Validator<String> {

    private static final String EMAIL_PATTERN = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";

    private Pattern pattern;

    private static EmailValidator emailValidator;

    public static EmailValidator getInstance()
    {
        if(emailValidator == null)
        {
            emailValidator = new EmailValidator();
            emailValidator.pattern = Pattern.compile(EMAIL_PATTERN);
        }
        return emailValidator;
    }

    @Override
    public boolean isValid(String email) {
        return emailValidator.pattern.matcher(email).matches();
    }
}
