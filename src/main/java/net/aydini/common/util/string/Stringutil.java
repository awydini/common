package net.aydini.common.util.string;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Jul 8, 2020
 */
public class Stringutil
{
    
    public static Scanner stream(String str)
    {
        return new Scanner(new ByteArrayInputStream(Base64Converter.decodeBase64ToString(str).getBytes()), "utf-8");
    }

}
