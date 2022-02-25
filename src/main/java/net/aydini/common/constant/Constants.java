package net.aydini.common.constant;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class Constants {

    /**
     * default page number
     */
    public final static Integer DEFAULT_PAGE_NUMBER = 0;


    /**
     * default page size
     */
    public final static Integer DEFAULT_PAGE_SIZ = 10;


    /**
     * track code header in APIs
     */
    public final static String TRACK_CODE_HEADER = "trackCode";

    /**
     *  instance of jackson object mapper
     */
    public final static ObjectMapper jacksonMapper = new ObjectMapper();


}
