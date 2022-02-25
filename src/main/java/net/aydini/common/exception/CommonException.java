package net.aydini.common.exception;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class CommonException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 4084647062865843881L;

    public CommonException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CommonException(String message)
    {
        super(message);
    }

    public CommonException()
    {
        super();
    }

    public CommonException(Throwable cause) {
        super(cause);
    }
}
