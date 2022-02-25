package net.aydini.common.util;

import java.io.File;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import net.aydini.common.constant.Constants;
import net.aydini.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class JsonUtil {

    private static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    /**converts a json string to a java object. 
     * in order to convert generic types use {@link #convertFromJson(String, TypeReference)} instead.
     *
     * @param <R> type of output
     * @param json a json string
     * @param clazz type of result
     * @return instance of R filled by json values
     *
     * @throws CommonException if any exception is thrown.
     */
    public static <R> R convertFromJson(String json,Class<R> clazz) {
        try {
            return Constants.jacksonMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("error mapping {} to {}",json,clazz.getSimpleName());
            throw new CommonException(e.getMessage(), e);
        }
    }


    /**converts a json string to a java object. use this method only for generic types.
     *
     * @param <R> type of output
     * @param json a json string
     * @param typeReference type of result
     * @return instance of R filled by json values
     *
     * @throws CommonException if any exception is thrown.
     */
    public static <R> R convertFromJson(String json,TypeReference<R> typeReference) {
        try {
            return Constants.jacksonMapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("error mapping {} to {}",json,typeReference.getType().getTypeName());
            throw new CommonException(e.getMessage(), e);
        }
    }



    /**converts a json string to a java object. use this method only for generic types.
     *
     * @param <R> type of output
     * @param jsonFile a a file containing a json string
     * @param typeReference type of result
     * @return instance of R filled by json values
     *
     * @throws CommonException if any exception is thrown.
     */

    public static <R> R convertFromJsonFile(File jsonFile,TypeReference<R> typeReference) {
        try {
            return Constants.jacksonMapper.readValue(jsonFile, typeReference);
        } catch (Exception e) {
            log.error("error reading file");
            throw new CommonException(e.getMessage(), e);
        }
    }


    /**
     *
     * @param object a java object that is needed to convert to json.
     * @return json String
     *
     * @throws CommonException if any exception is thrown.
     */
    public static String toJson(Object object) {
        try {
            return Constants.jacksonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("error in writing json : {}", e.getMessage());
            throw new CommonException(e.getMessage(), e);
        }
    }
}
