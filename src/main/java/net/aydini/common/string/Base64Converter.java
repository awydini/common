package net.aydini.common.string;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 *         11:05:34 AM
 */

public class Base64Converter
{

    private Base64Converter() {}
    
    /**
     * 
     * @param source
     * @return decoded String
     * 
     */
    public static byte[] decodeBase64ToByteArray(byte[] source)
    {
        if (source == null || source.length == 0) return null;
        return Base64.decodeBase64(source);
    }
    
    /**
     * 
     * @param source
     * @return decoded String
     * 
     */
    public static byte[] decodeBase64ToByteArray(String source)
    {
        if (StringUtils.isEmpty(source)) return null;
        return Base64.decodeBase64(source);
    }
    
    /**
     * 
     * @param source
     * @return decoded String
     * 
     */
    public static String decodeBase64ToString(String source)
    {
        if (StringUtils.isEmpty(source)) return null;
        return new String(Base64.decodeBase64(source), StandardCharsets.UTF_8);
    }
    
    
    /**
     * 
     * @param source
     * @return decoded String
     * 
     */
    public static String decodeBase64ToString(byte[] source)
    {
        if (source == null || source.length == 0) return null;
        return new String(Base64.decodeBase64(source), StandardCharsets.UTF_8);
    }
    

    /**
     * 
     * @param source
     * @return
     */
    public static byte[] encode(String source)
    {
        return StringUtils.isNotEmpty(source) ? encode(source.getBytes()): null;
    }
    
    /**
     * 
     * @param source
     * @return
     */
    public static byte[] encode(byte[] source)
    {
        if (source == null || source.length == 0) return null;
        return Base64.encodeBase64String(source).getBytes();
    }
    
    
    /**
     * 
     * @param source
     * @return
     */
    public static String encodeToString(String source)
    {
        if(StringUtils.isEmpty(source))
            return null;
        return Base64.encodeBase64String(source.getBytes());
    }
    
    
    /**
     * 
     * @param source
     * @return
     */
    public static String encodeToString(byte[] source)
    {
        if (source == null || source.length == 0) return null;
        return Base64.encodeBase64String(source);
    }
}
