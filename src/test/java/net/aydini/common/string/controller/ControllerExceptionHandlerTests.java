package net.aydini.common.string.controller;

import net.aydini.common.constant.Constants;
import net.aydini.common.controller.ControllerExceptionHandler;
import net.aydini.common.doamin.dto.web.ResponseError;
import net.aydini.common.exception.NotFoundException;
import net.aydini.common.exception.ProxyException;
import net.aydini.common.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class ControllerExceptionHandlerTests {

    private final  WebRequest webRequest = mock(WebRequest.class);


    private ControllerExceptionHandler controllerExceptionHandler;

    private String trackCode = "213456";


    @BeforeEach
    public void init()
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Constants.TRACK_CODE_HEADER,trackCode);
        when(webRequest.getHeader(Constants.TRACK_CODE_HEADER)).thenReturn(httpHeaders.getFirst(Constants.TRACK_CODE_HEADER));
        when(webRequest.getHeaderNames()).thenReturn(List.of(Constants.TRACK_CODE_HEADER).iterator());
        when(webRequest.getHeaderValues(Constants.TRACK_CODE_HEADER)).thenReturn(new String[]{trackCode});
        controllerExceptionHandler = new ControllerExceptionHandler() {
            @Override
            protected Logger getLogger() {
                return LoggerFactory.getLogger(this.getClass());
            }
        };
    }


    @Test
    public void test_handleNotFoundErro()
    {
        ResponseEntity<ResponseError> responseEntity = controllerExceptionHandler.handleNotFoundErro(new NotFoundException("not found"), webRequest);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getCode());
        assertEquals(HttpStatus.NOT_FOUND.name(), responseEntity.getBody().getMessage());
        assertEquals(trackCode,responseEntity.getHeaders().getFirst(Constants.TRACK_CODE_HEADER) );
    }



    @Test
    public void test_handleValidationEroor()
    {
        ResponseEntity<ResponseError> responseEntity = controllerExceptionHandler.handleValidationEroor(new ValidationException("not valid"), webRequest);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getCode());
        assertEquals(HttpStatus.BAD_REQUEST.name(), responseEntity.getBody().getMessage());
        assertEquals(trackCode,responseEntity.getHeaders().getFirst(Constants.TRACK_CODE_HEADER) );
    }


    @Test
    public void test_handleUndefinedException()
    {
        ResponseEntity<ResponseError> responseEntity = controllerExceptionHandler.handleUndefinedException(new RuntimeException("Exception"), webRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getCode());
        assertEquals("Exception", responseEntity.getBody().getMessage());
        assertEquals(trackCode,responseEntity.getHeaders().getFirst(Constants.TRACK_CODE_HEADER) );
    }


    @Test
    public void test_handleProxyException()
    {
        ResponseEntity<ResponseError> responseEntity = controllerExceptionHandler.handleProxyException(new ProxyException(new ResponseError("error", 504, "error")), webRequest);
        assertEquals(HttpStatus.GATEWAY_TIMEOUT, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.GATEWAY_TIMEOUT.value(), responseEntity.getBody().getCode());
        assertEquals("error", responseEntity.getBody().getMessage());
        assertEquals(trackCode,responseEntity.getHeaders().getFirst(Constants.TRACK_CODE_HEADER) );
    }
}
