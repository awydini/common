package net.aydini.common.exception;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public class ExcelException extends ServiceException{

    /**
     * 
     */
    private static final long serialVersionUID = -1615906167451547221L;

    public ExcelException(String message,Object[] args, Throwable cause) {
        super(message, cause,args);
    }

    public ExcelException(String message,Object ... args)
    {
        super(message,args);
    }

    public ExcelException(Throwable cause) {
        super(cause);
    }
}
