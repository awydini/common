package net.aydini.common.util.string;

import java.util.NoSuchElementException;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 * 
 * @Author <a href="mailto:paakro@gmail.com">Aydin Nasrollahpour </a>
 *
 * Jul 9, 2020
 */
public class SimpleTokenizerTests {

    private static String inputString;
    private static String[] names;

    
    public static enum Names
    {
        FROMTO,DEATH_DATE,MASKED_NATIONAL_CODE,BIRTH_DATE,FAMILY;
    }

    @BeforeClass
    public static void init()
    {
        inputString = "1398080113980801,13260110,003****297,13251229 ,family";

        int arrayLength = Names.values().length;
        names = new String[arrayLength];
        for(int i = 0 ; i< arrayLength ; i++)
            names[i]=Names.values()[i].name();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void tokensTest()
    {
        SimpleTokenizer tokenizer = new SimpleTokenizer(inputString,",");
        assertEquals(5,tokenizer.countTokens());

        assertEquals("1398080113980801",tokenizer.nextElement());
        assertEquals("13260110",tokenizer.nextElement());
        assertEquals("003****297",tokenizer.nextElement());
        assertEquals("13251229",tokenizer.nextElement());
        assertEquals("family",tokenizer.nextElement());

        expectedException.expect(NoSuchElementException.class);
        tokenizer.nextElement();

    }


    @Test
    public void tokenNamesTest()
    {
        SimpleTokenizer tokenizer = new SimpleTokenizer(inputString,",");
        tokenizer.setNames(names);
        assertEquals("1398080113980801",tokenizer.getStringToken(Names.FROMTO.name()));
        assertEquals("13251229",tokenizer.getStringToken(Names.BIRTH_DATE.name()));
        assertEquals("family",tokenizer.getStringToken(Names.FAMILY.name()));
        assertEquals("13260110",tokenizer.getStringToken(Names.DEATH_DATE.name()));
        assertEquals("003****297",tokenizer.getStringToken(Names.MASKED_NATIONAL_CODE.name()));

        long expectedBirthDate = 13260110l;
        long actualBirthDate= tokenizer.getLongToken(Names.DEATH_DATE.name());
        assertEquals(expectedBirthDate,actualBirthDate);

        expectedException.expect(NoSuchElementException.class);
        tokenizer.getStringToken("none");
    }
}