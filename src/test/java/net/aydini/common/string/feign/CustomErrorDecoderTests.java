package net.aydini.common.string.feign;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.stream.Stream;

import net.aydini.common.exception.NotFoundException;
import net.aydini.common.exception.ProxyException;
import net.aydini.common.exception.ValidationException;
import net.aydini.common.feign.CustomErrorDecoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.http.HttpStatus;

import feign.Request;
import feign.Request.HttpMethod;
import feign.Response;


/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class CustomErrorDecoderTests {

    public static class StatusArgumentProvide implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(Arguments.of(HttpStatus.NOT_ACCEPTABLE.value()),
                    Arguments.of(HttpStatus.BAD_REQUEST.value()));
        }

    }


    public static class ServerErrorStatusArgumentProvide implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(Arguments.of(HttpStatus.INTERNAL_SERVER_ERROR.value()),Arguments.of(HttpStatus.GATEWAY_TIMEOUT.value()),
                    Arguments.of(HttpStatus.BAD_GATEWAY.value()));
        }

    }

    @Test
    public void test_decode_ProxyExceptionForNullResponse() {

        Exception exception = new CustomErrorDecoder().decode(null, null);
        assertTrue(exception instanceof ProxyException);
    }

    @ParameterizedTest
    @ArgumentsSource(value = StatusArgumentProvide.class)
    public void test_decode_ValidationExceptionOn400And406HttpStatusCode(Integer status) {
        Response mockResponse = Response.builder().status(status).request(Request.create(HttpMethod.GET, "", new HashMap<>(), null, null,null)).build();
        Exception exception = new CustomErrorDecoder().decode(null, mockResponse);
        assertTrue(exception instanceof ValidationException);

    }


    @Test
    public void test_decode_NotFoundExceptionOn404HttpStatusCode() {
        Response mockResponse = Response.builder().status(HttpStatus.NOT_FOUND.value()).request(Request.create(HttpMethod.GET, "", new HashMap<>(), null, null,null)).build();
        Exception exception = new CustomErrorDecoder().decode(null, mockResponse);
        assertTrue(exception instanceof NotFoundException);

    }



    @ParameterizedTest
    @ArgumentsSource(value = ServerErrorStatusArgumentProvide.class)
    public void test_decode_ProxyExceptionServerErrors(Integer status) {
        Response mockResponse = Response.builder().status(status).request(Request.create(HttpMethod.GET, "", new HashMap<>(), null, null,null)).build();
        Exception exception = new CustomErrorDecoder().decode(null, mockResponse);
        assertTrue(exception instanceof ProxyException);

    }
}
