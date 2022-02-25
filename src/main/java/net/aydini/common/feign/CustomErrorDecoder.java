package net.aydini.common.feign;

import net.aydini.common.doamin.dto.web.ResponseError;
import net.aydini.common.exception.NotFoundException;
import net.aydini.common.exception.ProxyException;
import net.aydini.common.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import feign.Response;
import feign.codec.ErrorDecoder;




/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class CustomErrorDecoder implements ErrorDecoder{
    private final static Logger log = LoggerFactory.getLogger(CustomErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response)
    {
        if (response == null) return new ProxyException(new ResponseError("unexpected error in "+methodKey,HttpStatus.INTERNAL_SERVER_ERROR.value()));

        log.error("response {}", response.toString());
        if(response.status() == HttpStatus.NOT_ACCEPTABLE.value() || response.status()==HttpStatus.BAD_REQUEST.value())
            return new ValidationException();
        else if(response.status() == HttpStatus.NOT_FOUND.value())
            return new NotFoundException(NotFoundException.NOT_FOUND);

        return new ProxyException(new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.name(), response.status(),HttpStatus.INTERNAL_SERVER_ERROR.name()));
    }
}
