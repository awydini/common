package net.aydini.common.doamin.dto.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class ResponseError extends ResponseMessage{
    /**
     *
     */
    private static final long serialVersionUID = -2061705289597928352L;



    /**
     * list of API errors.
     */
    private List<String> errors;


    /**
     *
     * @param message
     * @param code
     * @param errors
     */
    public ResponseError(String message, Integer code, String ... errors)
    {
        super(message, code);
        this.errors = errors != null ? Arrays.asList(errors) : new ArrayList<>();
    }


    /**
     *
     * @param message
     * @param code
     * @param errors
     */
    public ResponseError(String message, Integer code, List<String> errors)
    {
        super(message, code);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
