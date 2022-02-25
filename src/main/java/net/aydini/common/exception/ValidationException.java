package net.aydini.common.exception;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public class ValidationException extends ServiceException{

    /**
     * 
     */
    private static final long serialVersionUID = -1615906167451547221L;

    public ValidationException(String message,Object[] args, Throwable cause) {
        super(message, cause,args);
    }

    public ValidationException(String message,Object ... args)
    {
        super(message,args);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
    public ValidationException() {
    }

}
