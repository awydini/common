package net.aydini.common.exception;

import net.aydini.common.doamin.dto.web.ResponseError;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class ProxyException extends CommonException{

    /**
     *
     */
    private static final long serialVersionUID = 6653413429882300831L;


    private final ResponseError responseError;

    public ProxyException(String message, Throwable cause,ResponseError responseError)
    {
        super(message, cause);
        this.responseError=responseError;
    }

    public ProxyException(ResponseError responseError)
    {
        super(responseError.getMessage());
        this.responseError=responseError;
    }

    public ResponseError getResponseError() {
        return responseError;
    }
}
