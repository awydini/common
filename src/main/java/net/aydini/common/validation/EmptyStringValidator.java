package net.aydini.common.validation;

import org.apache.commons.lang3.StringUtils;

import net.aydini.common.exception.ValidationException;

public class EmptyStringValidator implements ArgumentValidator<String>
{

    @Override
    public boolean isValid(String... args) throws ValidationException
    {
        if (args == null || args.length == 0) throw new ValidationException("emty args");
        for (String str : args)
            if (StringUtils.isAllEmpty(str)) 
                return false;
        return true;
    }

}
