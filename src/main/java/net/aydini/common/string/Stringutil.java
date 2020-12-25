package net.aydini.common.string;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jul 8, 2020
 */
public class Stringutil
{
    
    public static Scanner stream(String str)
    {
        return new Scanner(new ByteArrayInputStream(Base64Converter.decodeBase64ToString(str).getBytes()), "utf-8");
    }


    public static String
    toLowerFirstLetter(String string)
    {
        if(StringUtils.isEmpty(string))
            throw new NullPointerException("empty string");
        if(string.length() == 1)
            return string;
        return Character.toLowerCase(string.charAt(0)) +string.substring(1);
    }

}
