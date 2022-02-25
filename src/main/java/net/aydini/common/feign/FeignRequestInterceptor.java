package net.aydini.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**this class is responsible for logging request
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    private final static Logger log = LoggerFactory.getLogger(FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate template)
    {
        if(template == null)
        {
            log.error("request template is null.");
            return;
        }
        log.info("----------begin request---------- ");
        log.info("request data : \n {}", template.toString());
        log.info("queries : {} ", template.queries());
        log.info("headers : {} ", template.headers());
        log.info("uri : {} ", template.url());
        log.info("----------end request---------- ");
    }
}
