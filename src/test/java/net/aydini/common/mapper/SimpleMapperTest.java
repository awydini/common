package net.aydini.common.mapper;

import net.aydini.common.doamin.PersonDto;
import net.aydini.common.doamin.PersonModel;
import static  org.junit.jupiter.api.Assertions.*;

import net.aydini.common.doamin.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public class SimpleMapperTest {

    private PersonDto personDto;
    SimpleEntityMapper simpleEntityMapper = new SimpleEntityMapper();
    @BeforeEach
    public void init()
    {
        personDto = new PersonDto();
        personDto.setFamily("Nasrollahpour");
        personDto.setName("Aydin");

        UserDto userDto = new UserDto();
        userDto.setId(1l);
        userDto.setUserName("awydini");

        personDto.setUser(userDto);
    }

    @Test
    public void mapTest()
    {
        PersonModel personModel = simpleEntityMapper.map(personDto, PersonModel.class);
        assertEquals(personDto.getName(),personModel.getName());
        assertEquals(personDto.getFamily(),personModel.getFamily());
    }


    @Test
    public void mapCompositeModelTest()
    {
        PersonModel personModel = simpleEntityMapper.map(personDto, PersonModel.class);
        assertNotNull(personModel.getUser());
        assertEquals(personDto.getUser().getUserName(),personModel.getUser().getUserName());
        assertEquals(personDto.getUser().getId(),personModel.getUser().getId());
    }
}
