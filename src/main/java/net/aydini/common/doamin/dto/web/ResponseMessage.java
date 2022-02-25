package net.aydini.common.doamin.dto.web;

import net.aydini.common.doamin.dto.BaseDto;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class ResponseMessage implements BaseDto {

    /**
     *
     */
    private static final long serialVersionUID = 8954918859796348384L;

    /**
     *  response message
     */
    private String message;


    /**
     * response code
     */
    private Integer code;

    public ResponseMessage() {
    }

    public ResponseMessage(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
