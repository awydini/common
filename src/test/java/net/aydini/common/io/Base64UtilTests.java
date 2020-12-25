package net.aydini.common.io;

import net.aydini.common.string.Base64Converter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @Author  Aydin Nasrollahpour
 *
 * Jun 28, 2020
 */
public class Base64UtilTests
{

    private final String base64Str="ZGVjb2RlciB0ZXN0";

    private final String decodedBase64Str="decoder test";



    @Test
    public void decodeBase64ToByteArrayTest()
    {
        byte[] base64 = Base64Converter.decodeBase64ToByteArray(base64Str);
        assertArrayEquals(decodedBase64Str.getBytes(), base64);
        byte[] b = null;
        assertNull(Base64Converter.decodeBase64ToByteArray(b));

        byte[] byteArrayBase64 = Base64Converter.decodeBase64ToByteArray(base64Str.getBytes());
        assertArrayEquals(decodedBase64Str.getBytes(), byteArrayBase64);

        String s = null;
        assertNull(Base64Converter.decodeBase64ToByteArray(s));
    }



    @Test
    public void decodeBase64ToStringTest()
    {
        String decodedString = Base64Converter.decodeBase64ToString(base64Str);
        assertEquals(decodedBase64Str, decodedString);
        String s= null;
        assertNull(Base64Converter.decodeBase64ToString(s));

        decodedString = Base64Converter.decodeBase64ToString(base64Str.getBytes());
        assertEquals(decodedBase64Str, decodedString);
        byte[] b= null;
        assertNull(Base64Converter.decodeBase64ToString(b));
    }


    @Test
    public void encodeTest()
    {
       byte[] encoded = Base64Converter.encode(decodedBase64Str);
       assertArrayEquals(base64Str.getBytes(), encoded);
       String s = null;
       assertNull(Base64Converter.encode(s));


       encoded = Base64Converter.encode(decodedBase64Str.getBytes());
       assertArrayEquals(base64Str.getBytes(), encoded);
       byte[] b = null;
       assertNull(Base64Converter.encode(b));


    }



    @Test
    public void encodeToStringTest()
    {
        String encoded = Base64Converter.encodeToString(decodedBase64Str);
        assertEquals(base64Str, encoded);
        String s = null;
        assertNull(Base64Converter.encodeToString(s));


        encoded = Base64Converter.encodeToString(decodedBase64Str.getBytes());
        assertEquals(base64Str, encoded);
        byte[] b = null;
        assertNull(Base64Converter.encodeToString(b));
    }

    
}
