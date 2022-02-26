package net.aydini.common.exception;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class NotFoundException extends CommonException{

    /**
     *
     */
    private static final long serialVersionUID = 6653413429882331831L;



    public static final String DATA_WITH_ID_NOT_FOUND= "DATA WITH ID NOT FOUND. ID = ";

    public static final String NOT_FOUND= " NOT FOUND";

    public NotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NotFoundException(String message)
    {
        super(message);
    }

    public NotFoundException()
    {
        super();
    }
}
