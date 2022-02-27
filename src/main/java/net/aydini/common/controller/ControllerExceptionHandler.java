package net.aydini.common.controller;

import net.aydini.common.doamin.dto.web.ResponseError;
import net.aydini.common.exception.NotFoundException;
import net.aydini.common.exception.ProxyException;
import net.aydini.common.exception.ValidationException;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Base class for any controller advice class. this class contains handler methods for different exception types.
 *
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public abstract class ControllerExceptionHandler  extends ResponseEntityExceptionHandler {
    /**
     *
     * @return singleton instance of org.slf4j.Logger
     */
    protected abstract Logger getLogger();



    /** handle's NotFoundException
     *
     * @param ex a NotFoundException injected by framework
     * @param request WebRequest injected by framework
     *
     * @return ResponseEntity with 404 http code
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handleNotFoundErro(NotFoundException ex, WebRequest request)
    {
        getLogger().error("not found error!. {} ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(extractHttpHeaders(request))
                .body(new ResponseError(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),ex.getMessage()));
    }


    /** handle's ValidationException
     *
     * @param ex a ValidationException injected by framework
     * @param request WebRequest injected by framework
     *
     * @return ResponseEntity with 400 http code
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ResponseError> handleValidationEroor(ValidationException ex,WebRequest request)
    {
        getLogger().error("validation error!. {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(extractHttpHeaders(request))
                .body(new ResponseError(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(),ex.getMessage()));
    }


    /** handle's MethodArgumentNotValidException
     *
     * @param ex a MethodArgumentNotValidException injected by framework
     * @param request WebRequest injected by framework
     * @param headers http headers injected by framework
     * @param status Http status injected by framework
     *
     * @return ResponseEntity with 406 http code
     */
    @Override
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex.getBindingResult().getFieldErrors().stream().map(item->item.getDefaultMessage()).collect(Collectors.toList());
        getLogger().error("validation error!. {}", errorList);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .headers(headers).body(new ResponseError(HttpStatus.NOT_ACCEPTABLE.name(), HttpStatus.NOT_ACCEPTABLE.value(),errorList));
    }



    /** handle's Exception
     *
     * @param ex a Exception injected by framework
     * @param request WebRequest injected by framework
     *
     * @return ResponseEntity with 500 http code
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleUndefinedException(Exception ex,WebRequest request)
    {
        getLogger().error("error : {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(extractHttpHeaders(request))
                .body(new ResponseError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage()));
    }




    /** handle's ProxyException
     *
     * @param ex a ProxyException injected by framework
     * @param request WebRequest injected by framework
     *
     * @return ResponseEntity with 502 http code
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(ProxyException.class)
    public ResponseEntity<ResponseError> handleProxyException(ProxyException ex,WebRequest request)
    {
        getLogger().error("error : {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getResponseError().getCode())).headers(extractHttpHeaders(request))
                .body(new ResponseError(ex.getResponseError().getMessage(), ex.getResponseError().getCode(),ex.getResponseError().getErrors().iterator().next()));
    }



    /**
     * handler for other exceptions
     *
     * @return ResponseEntity with 500 http code
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return  ResponseEntity.status(status).headers(extractHttpHeaders(request)).body(new ResponseError(ex.getMessage(), status.value(),ex.getMessage()));
    }


    private HttpHeaders extractHttpHeaders(WebRequest webRequest)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        if(webRequest == null || webRequest.getHeaderNames() == null)
            return httpHeaders;
       webRequest.getHeaderNames().forEachRemaining(headerName->httpHeaders.addAll(headerName, List.of(webRequest.getHeaderValues(headerName))));
        return httpHeaders;
    }
}
