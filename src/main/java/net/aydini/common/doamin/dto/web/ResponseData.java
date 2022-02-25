package net.aydini.common.doamin.dto.web;

import net.aydini.common.doamin.dto.BaseDto;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class ResponseData<T extends BaseDto> extends ResponseMessage {
    /**
     *
     */
    private static final long serialVersionUID = 9053605135880027316L;
    /**
     * response data
     */
    private T data;


    public ResponseData()
    {
        super();
    }

    public ResponseData(String message, Integer code, T data)
    {
        super(message, code);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
