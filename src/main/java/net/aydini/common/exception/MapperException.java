package net.aydini.common.exception;
/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Jul 9, 2020
 */
public class MapperException extends ServiceException {

    /**
     * 
     */
    private static final long serialVersionUID = -7595048830886191432L;



    public MapperException(String message) {
        super(message);
    }

    public MapperException(String message , Throwable throwable) {
        super(message ,throwable);
    }

    public MapperException(String message,Object[] args, Throwable cause) {
        super(message, cause,args);
    }

}